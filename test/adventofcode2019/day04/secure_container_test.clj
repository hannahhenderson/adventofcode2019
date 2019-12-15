(ns adventofcode2019.day04.secure-container-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.day04.secure-container :as sc]))

(deftest digit-vector-works
  (is (= [1 2 3]
         (sc/digit-vector 123))))

(deftest digits-increase?-works
  (is (true? (sc/digits-increase? (sc/digit-vector 111123))))
  (is (true? (sc/digits-increase? (sc/digit-vector 135679))))
  (is (true? (sc/digits-increase? (sc/digit-vector 111111))))
  (is (false? (sc/digits-increase? (sc/digit-vector 223450)))))

(deftest has-adjacent-digits?-works
  (is (true? (sc/has-adjacent-digits? (sc/digit-vector 111123))))
  (is (true? (sc/has-adjacent-digits? (sc/digit-vector 223450))))
  (is (false? (sc/has-adjacent-digits? (sc/digit-vector 123789)))))

(deftest is-valid-works
  (is (true? (sc/is-valid? 111111)))
  (is (false? (sc/is-valid? 223450)))
  (is (false? (sc/is-valid? 123789))))

(deftest total-valid-passwords-works
  (testing "some shorter base cases"
    (is (= 9
           (sc/total-valid-passwords 109 121)))
    (is (= 0
           (sc/total-valid-passwords 309 321))))
  (testing "start and end values are included"
    (is (= 9
           (sc/total-valid-passwords 111 119)))))
