(ns adventofcode2019.challenges.day1.fuel-counter)


;  --- Day 1: The Tyranny of the Rocket Equation ---
;
;  Santa has become stranded at the edge of the Solar System while delivering presents to other planets!
;  To accurately calculate his position in space, safely align his warp drive, and return to Earth in time to save
;  Christmas, he needs you to bring him measurements from fifty stars.
;
;  Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar;
;  the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
;
;  The Elves quickly load you into a spacecraft and prepare to launch.
;
;  At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper. They haven't determined the amount of
;  fuel required yet.
;
;  Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module,
;  take its mass, divide by three, round down, and subtract 2.
;
;  For example:
;
;  For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
;  For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
;  For a mass of 1969, the fuel required is 654.
;  For a mass of 100756, the fuel required is 33583.
;
;  The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed
;  for the mass of each module (your puzzle input), then add together all the fuel values.
;
;  What is the sum of the fuel requirements for all of the modules on your spacecraft?
;
;  To begin, get your puzzle input.

(defn file->vector-by-line
  "filename path must come from root

  e.g. src/adventofcode2019/challenges/day1/fuel-counter-input.txt"
  [filename]
  (-> (slurp filename)
      clojure.string/split-lines))

(defn ->int [x]
  "Coerce input to int, or values within vector input to int

  Minimal checking, if you want to throw the kitchen sink at this
  equation, you'll need to make the cond more robust"
  (cond
    (vector? x) (map (fn [y] (Integer. y)) x)
    :else (Integer. x)))

(defn mass->fuel-required
  [mass]
  (-> (/ mass 3)
      Math/floor
      int
      (- 2)))

(defn total-fuel-required [mass-inputs]
  (reduce (fn [acc mass]
            (+ acc (mass->fuel-required mass)))
          0
          mass-inputs))

(defn file->total-fuel [input-filename]
  (->> (file->vector-by-line input-filename)
       ->int
       total-fuel-required))

(println "Total: " (file->total-fuel "src/adventofcode2019/challenges/day1/fuel-counter-input.txt"))
