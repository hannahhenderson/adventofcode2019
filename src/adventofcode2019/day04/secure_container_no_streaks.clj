(ns adventofcode2019.day04.secure-container-no-streaks
  (:require [adventofcode2019.utils :as utils]
            [adventofcode2019.day04.secure-container :as sc]))

(defn safe-nth
  [v pos]
  (cond
    (> 0 pos)
    nil

    (<= (count v) pos)
    nil

    :else
    (nth v pos)))

(defn zero-streak?
  [v pos]
  (when (= 0 pos)
    (= (nth v 0)
       (nth v 1)
       (nth v 2))))

(defn final-streak?
  ; assumes length of vector is six digits a streak is 3+ vals
  [v pos]
  (when (= 4 pos)
    (= (nth v 3)
       (nth v 4)
       (nth v 5))))

(defn middle-streak?
  [v pos]
  (or (= (safe-nth v (- pos 2))
         (safe-nth v (- pos 1))
         (safe-nth v pos))
      (= (safe-nth v (- pos 1))
         (safe-nth v pos)
         (safe-nth v (+ pos 1)))
      (= (safe-nth v (+ pos 2))
         (safe-nth v (+ pos 1))
         (safe-nth v pos))))

(defn in-streak?
  [v pos]
  (or (final-streak? v pos)
      (zero-streak? v pos)
      (middle-streak? v pos)))

(defn has-isolated-pair?
  [pw-vector]
  (let [length (count pw-vector)]
    (loop [pos 0
           isolated-pair? false]
      (cond
        (>= pos length)
        isolated-pair?

        (and (not (in-streak? pw-vector pos))
             (= (safe-nth pw-vector pos) (safe-nth pw-vector (+ pos 1))))
        true

        :else
        (recur (+ 1 pos) false)))))


(defn is-valid?
  [pw]
  (let [pw-vector (sc/digit-vector pw)]
    (and (has-isolated-pair? pw-vector)
         (sc/digits-increase? pw-vector))))

(defn not-valid?
  ; double negatives are confusing
  [pw]
  (not (is-valid? pw)))

(defn total-valid-passwords
  [range-start range-end]
  (->> (range range-start (+ 1 range-end))  ; include range start and end
       (remove not-valid?)
       count))

;TODO: Uncomment me to get the answer -- I'm a poorly optimized bit of logic!
;(println "result is: " (total-valid-passwords 125730 579381))