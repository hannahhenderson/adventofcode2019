(ns adventofcode2019.day03.crossed-wires
  (:require [adventofcode2019.utils :as utils]))

(defn generate-path-points [{:keys [x? mutate-start static-start steps sign-fn]}]
  (let [vals (range 0  steps)]
    (reduce (fn [acc val]
              (if x?
                (conj acc [(+ mutate-start (sign-fn val)) static-start])
                (conj acc [static-start (+ mutate-start (sign-fn val))])))
            []
            vals)))

(defn pos [a] (identity a))
(defn neg [a] (* -1 a))

(defn udrl->x?-sign-fn [direction]
  (condp = direction
    "U" [false pos]
    "D" [false neg]
    "R" [true pos]
    "L" [true neg]
    (throw (ex-info "check yo signs" {}))))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s )))

(defn points-along-path-fragment
  [start-coord pfrag]
  (let [[x? sign-fn] (udrl->x?-sign-fn (subs pfrag 0 1))
        steps (parse-int (subs pfrag 1))
        mutate-start (if x? (first start-coord) (second start-coord))
        static-start (if x? (second start-coord) (first start-coord))]
    (generate-path-points {:x? x?
                           :mutate-start mutate-start
                           :static-start static-start
                           :steps (+ steps 1)
                           :sign-fn sign-fn})))

(defn path-points [path]
  (loop [prest path
         coordinates [[0 0]]]
    (if (empty? prest)
      coordinates
      (let [start (last coordinates)
            nextp (first prest)]
        (recur (rest prest) (concat (drop-last coordinates) (points-along-path-fragment start nextp)))))))

(defn manhattan-distance [[x y]]
  ; Assumption: [x1 y1] is [0 0]
  ; Equation: |x1 - x2| + |y1 - y2|
  (let [abs-x (max x (* -1 x))
        abs-y (max y (* -1 y))]
    (+ abs-x abs-y)))

(defn wire-paths [wire1 wire2]
  (let [wire1-points (path-points wire1)
        wire2-points (path-points wire2)
        ;; Exclude from overlaps:
        ;;   - central point where both wires start
        ;;   - when a wire crosses over itself
        overlaps (-> (clojure.set/intersection (set wire1-points)
                                               (set wire2-points))
                     (disj [0 0]))]
    (->> overlaps
         vec
         (map manhattan-distance)
         (apply min))))

(defn min-manhattan
  [filename]
  (let [wire-directions (utils/file->vectors-of-directions filename)]
    (wire-paths (first wire-directions) (second wire-directions))))

(println "result is: " (min-manhattan "day03/wire-inputs.txt"))