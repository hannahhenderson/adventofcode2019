(ns adventofcode2019.day03.crossed-wires-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.day03.crossed-wires :as cw]))

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
  (is (= [[0 0] [0 1] [0 2] [0 3] [0 4] [0 5] [0 6]]
         (cw/points-along-path-fragment [0 0] "U7")))
  (is (= [[0 6] [1 6] [2 6] [3 6] [4 6] [5 6]]
         (cw/points-along-path-fragment [0 6] "R6")))
  (is (= [[5 6] [5 5] [5 4] [5 3]]
         (cw/points-along-path-fragment [5 6] "D4")))
  (is (= [[5 3] [4 3] [3 3] [2 3]]
         (cw/points-along-path-fragment [5 3] "L4"))))

(deftest path-points-works
  (is (= [[0 0] [0 1] [0 2] [0 3] [0 4] [0 5]
          [0 6] [1 6] [2 6] [3 6] [4 6]
          [5 6] [5 5] [5 4]
          [5 3] [4 3] [3 3] [2 3]]
         (cw/path-points ["U7" "R6" "D4" "L4"]))))
