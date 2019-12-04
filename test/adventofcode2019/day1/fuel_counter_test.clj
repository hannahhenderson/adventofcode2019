(ns adventofcode2019.day1.fuel-counter-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.challenges.day1.fuel-counter :as fc]))

(deftest mass->fuel-works
  (testing "provided examples"
    (is (= 2
           (fc/mass->fuel 12)))
    (is (= 2
           (fc/mass->fuel 14)))
    (is (= 654
           (fc/mass->fuel 1969)))
    (is (= 33583
           (fc/mass->fuel 100756)))))

(deftest fuel-for-modules-works
  (is (= (+ 2 2)
         (fc/fuel-for-modules [12 14]))))

(deftest mass->fuel-and-its-fuel-works
  (is (= 2
         (fc/mass->fuel-and-its-fuel 14)))
  (is (= 966
         (fc/mass->fuel-and-its-fuel 1969)))
  (is (= 50346
         (fc/mass->fuel-and-its-fuel 100756))))

(deftest fuel-for-modules-and-fuel-works
  (is (= (+ 2 966)
         (fc/fuel-for-modules-and-fuel [14 1969]))))