(ns adventofcode2019.utils
  (require [clojure.java.io :as io]))

(defn file->vector-by-line
  "From root in `resources` folder

   e.g. day1/fuel-counter-input.txt"
  [filepath]
  (-> (slurp (io/resource filepath))
      clojure.string/split-lines))

(defn ->int [x]
  "Coerce input to int, or values within vector input to int

  Minimal checking, if you want to throw the kitchen sink at this
  equation, you'll need to make the cond more robust"
  (cond
    (vector? x) (map (fn [y] (Integer. y)) x)
    :else (Integer. x)))
