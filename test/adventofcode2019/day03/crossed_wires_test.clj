(ns adventofcode2019.day03.crossed-wires-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.day03.crossed-wires :as cw]
            [adventofcode2019.utils :as utils]))

(deftest generate-path-points-works
  (testing "it outputs properly for x positive steps"
    (is (= [[10 0] [11 0] [12 0]]
           (cw/generate-path-points {:x? true
                                     :mutate-start 10
                                     :static-start 0
                                     :steps 3
                                     :sign-fn cw/pos}))))

  (testing "it outputs properly for x negative steps"
    (is (= [[10 0] [9 0] [8 0] [7 0]]
           (cw/generate-path-points {:x? true
                                     :mutate-start 10
                                     :static-start 0
                                     :steps 4
                                     :sign-fn cw/neg}))))
  (testing "it outputs properly for y positive steps"
    (is (= [[0 10] [0 11] [0 12]]
           (cw/generate-path-points {:x? false
                                     :mutate-start 10
                                     :static-start 0
                                     :steps 3
                                     :sign-fn cw/pos}))))
  (testing "it outputs properly for y negative steps"
    (is (= [[0 10] [0 9] [0 8] [0 7]]
           (cw/generate-path-points {:x? false
                                     :mutate-start 10
                                     :static-start 0
                                     :steps 4
                                     :sign-fn cw/neg})))))

(deftest points-along-path-fragment-works
  (is (= [[0 0] [0 1] [0 2] [0 3] [0 4] [0 5] [0 6] [0 7]]
         (cw/points-along-path-fragment [0 0] "U7")))
  (is (= [[0 6] [1 6] [2 6] [3 6] [4 6] [5 6] [6 6]]
         (cw/points-along-path-fragment [0 6] "R6")))
  (is (= [[5 6] [5 5] [5 4] [5 3] [5 2]]
         (cw/points-along-path-fragment [5 6] "D4")))
  (is (= [[5 3] [4 3] [3 3] [2 3] [1 3]]
         (cw/points-along-path-fragment [5 3] "L4"))))

(deftest path-points-works
  (is (= [[0 0]
          [0 1] [0 2] [0 3] [0 4] [0 5] [0 6] [0 7]
          [1 7] [2 7] [3 7] [4 7] [5 7] [6 7]
          [6 6] [6 5] [6 4] [6 3]
          [5 3] [4 3] [3 3] [2 3]]
         (cw/path-points ["U7" "R6" "D4" "L4"]))))

(deftest wire-paths-works
  (testing "when a path overlaps with itself, that will not show up in clojure.set/intersection"
    (is (= #{[2 2]}
           (clojure.set/intersection (set [[0 1] [2 2] [3 3] [3 3]])
                                     (set [[1 1] [2 2]])))))
  (testing "examples"
    (is (= 6
           (cw/wire-paths ["R8" "U5" "L5" "D3"] ["U7" "R6" "D4" "L4"])))
    (is (= 159
           (cw/wire-paths ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
                          ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"])))
    (is (= 135
           (cw/wire-paths ["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"]
                          ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"])))))

(deftest min-manhattan-works
  (is (= 159
         (cw/min-manhattan "day03/test-inputs.txt"))))