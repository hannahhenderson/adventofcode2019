(ns adventofcode2019.day04.secure-container-no-streaks-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.day04.secure-container :as sc]
            [adventofcode2019.day04.secure-container-no-streaks :as sc-ns]))

(deftest has-adjacent-digits?-works
  (is (true? (sc-ns/has-isolated-pair? (sc/digit-vector 100033))))
  (is (true? (sc-ns/has-isolated-pair? (sc/digit-vector 112233))))
  (is (false? (sc-ns/has-isolated-pair? (sc/digit-vector 111111))))
  (is (false? (sc-ns/has-isolated-pair? (sc/digit-vector 111123))))
  (is (true? (sc-ns/has-isolated-pair? (sc/digit-vector 112113))))
  (is (false? (sc-ns/has-isolated-pair? (sc/digit-vector 123444))))
  (is (true? (sc-ns/has-isolated-pair? (sc/digit-vector 223450))))
  (is (true? (sc-ns/has-isolated-pair? (sc/digit-vector 111122))))
  (is (false? (sc-ns/has-isolated-pair? (sc/digit-vector 123789)))))

(deftest is-valid-works
  (is (false? (sc-ns/is-valid? 111111)))
  (is (true? (sc-ns/is-valid? 233344)))
  (is (false? (sc-ns/is-valid? 223450)))
  (is (false? (sc-ns/is-valid? 123789))))

(deftest total-valid-passwords-works
  (testing "some shorter base cases"
    (is (= 8
           (sc-ns/total-valid-passwords 111 121)))
    (is (= 0
           (sc-ns/total-valid-passwords 309 321))))
  (testing "start and end values are included"
    (is (= 8
           (sc-ns/total-valid-passwords 111 119)))))
