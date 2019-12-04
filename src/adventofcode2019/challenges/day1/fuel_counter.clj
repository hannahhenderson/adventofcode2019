(ns adventofcode2019.challenges.day1.fuel-counter
  (:require [adventofcode2019.utils :as utils]))

;; PART 1: MODULE FUEL
(defn mass->fuel
  [mass]
  (-> (/ mass 3)
      Math/floor
      int
      (- 2)))

(defn fuel-for-modules
  [mass-inputs]
  (reduce (fn [acc mass]
            (+ acc (mass->fuel mass)))
          0
          mass-inputs))

(defn file->fuel-for-modules
  [input-filename]
  (->> (utils/file->vector-by-line input-filename)
       utils/->int
       fuel-for-modules))

(println "Module fuel only: " (file->fuel-for-modules "day1/fuel-counter-input.txt"))

;; PART 2: FUEL'S FUEL
(defn mass->fuel-and-its-fuel
  [module-mass]
  (loop [sum 0
         mass module-mass]
    (let [fuel-req (mass->fuel mass)]
      (if (<= fuel-req 0)
        sum
        (recur (+ fuel-req sum) fuel-req)))))

(defn fuel-for-modules-and-fuel
  [mass-inputs]
  (reduce (fn [acc mass]
            (+ acc (mass->fuel-and-its-fuel mass)))
          0
          mass-inputs))

(defn file->fuel-for-modules-and-fuel
  [input-filename]
  (->> (utils/file->vector-by-line input-filename)
       utils/->int
       fuel-for-modules-and-fuel))

(println "Fuel for module AND fuel: " (file->fuel-for-modules-and-fuel "day1/fuel-counter-input.txt"))