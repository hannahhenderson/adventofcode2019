(ns adventofcode2019.utils
  (require [clojure.java.io :as io]
           [clojure.string :as str]))

(defn file->vector-by-line
  "From root in `resources` folder

   e.g. day1/fuel-counter-input.txt"
  [filepath]
  (-> (slurp (io/resource filepath))
      str/split-lines))

(defn ->int [x]
  "Coerce input to int, or values within vector input to int

  Minimal checking, if you want to throw the kitchen sink at this
  equation, you'll need to make the cond more robust"
  (cond
    (vector? x) (map (fn [y] (Integer. y)) x)
    :else (Integer. x)))

(defn file->comma-free-vector
  [filepath]
  (-> (slurp (io/resource filepath))
      (str/split #",")))

(defn file->vectors-of-directions
  [filepath]
  (->> (slurp (io/resource filepath))
       str/split-lines
       (map (fn [s] (str/split s #",")))))

(defn prn-return [x]
  (println x)
  x)
