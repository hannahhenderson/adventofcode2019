(ns adventofcode2019.day1.fuel-counter-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.challenges.day1.fuel-counter :as fc]))

(deftest mass->fuel-required-works
  (testing "provided examples"
    (is (= 2
           (fc/mass->fuel-required 12)))
    (is (= 2
           (fc/mass->fuel-required 14)))
    (is (= 654
           (fc/mass->fuel-required 1969)))
    (is (= 33583
           (fc/mass->fuel-required 100756)))))

(deftest total-fuel-required-works
  (is (= (+ 2 2)
         (fc/total-fuel-required [12 14]))))