(ns adventofcode2019.day03.crossed-wires)

; Don't count:
;   - central point where there both start
;   - wire crossing with itself

; https://en.wikipedia.org/wiki/Taxicab_geometry


; Why this isn't called the pythorean theorem, i do not know

; todo: generate all the points along the line
;       take the unique set
;       see which one is the furthest from the start point


;  but what about grid size?
;  convert values to x and y! assume start is 0,0


; U7,R6,D4,L4

; Everything in here is based on X,Y pairs

(defn generate-path-points [{:keys [x? mutate-start static-start steps sign-fn]}]
  (let [vals (range 0 steps)]
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
  (println start-coord pfrag)
  (let [[x? sign-fn] (udrl->x?-sign-fn (subs pfrag 0 1))
        steps (parse-int (subs pfrag 1))
        mutate-start (if x? (first start-coord) (second start-coord))
        static-start (if x? (second start-coord) (first start-coord))]
    (generate-path-points {:x? x?
                           :mutate-start mutate-start
                           :static-start static-start
                           :steps steps
                           :sign-fn sign-fn})))

(defn path-points [path]
  (loop [prest path
         coordinates [[0 0]]]
    (if (empty? prest)
      coordinates
      (let [_ (println coordinates)
            start (last coordinates)
            nextp (first prest)]
        (recur (rest prest) (concat (drop-last coordinates) (points-along-path-fragment start nextp)))))))


(defn wire-paths [wire1 wire2]
  (let [wire1-points (path-points wire1)
        wire2-points (path-points wire2)]
    ; get the overlap set (not the diff)
    ; which is closest to the start?
      ; assumption: they have the same start
    ; something to do with the manhattan distance?
    ))
