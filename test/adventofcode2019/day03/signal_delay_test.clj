(ns adventofcode2019.day03.signal-delay-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.day03.signal-delay :as sd]))

(deftest min-crossed-wire-path*-works
  (is (= 30
         (sd/min-crossed-wire-path* ["R8" "U5" "L5" "D3"] ["U7" "R6" "D4" "L4"])))

  (is (= 610
         (sd/min-crossed-wire-path* ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
                                    ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"])))

  (is (= 410
         (sd/min-crossed-wire-path* ["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"]
                                    ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"]))))

(deftest min-crossed-wire-path-works
  (is (= 610
         (sd/min-crossed-wire-path "day03/test-inputs.txt"))))