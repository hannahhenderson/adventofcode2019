(ns adventofcode2019.day03.signal-delay
  (:require [adventofcode2019.day03.crossed-wires :as cw]
            [adventofcode2019.utils :as utils]))

(defn step-length [step]
  (subs step 0 1))

(defn steps-to-overlap [wire-points overlaps]
  (map (fn [overlap]
         (.indexOf wire-points overlap))
       overlaps))

(defn min-crossed-wire-path* [wire1 wire2]
  (let [wire1-points (cw/path-points wire1)
        wire2-points (cw/path-points wire2)
        overlaps (cw/overlaps wire1-points wire2-points)
        wire1-steps-to-overlap (steps-to-overlap wire1-points overlaps)
        wire2-steps-to-overlap (steps-to-overlap wire2-points overlaps)]
    (->> (map + wire1-steps-to-overlap wire2-steps-to-overlap)
         (apply min))))

(defn min-crossed-wire-path
  [filename]
  (let [wire-directions (utils/file->vectors-of-directions filename)]
    (min-crossed-wire-path* (first wire-directions) (second wire-directions))))

 ;TODO: Uncomment me to get the answer -- I'm a poorly optimized bit of logic!
 ;(println "result is: " (min-crossed-wire-path "day03/wire-inputs.txt"))