(ns s99clj.core-test
  (:require [clojure.test :refer :all]
            [s99clj.core :refer :all]))

(deftest p01
  (testing "P01"
    (is (= 3 (my-last '(1 2 3))))
    (is (= 1 (my-last '(1))))
    (is (= nil (my-last '())))
    (is (= nil (my-last nil)))))

(deftest p02
  (testing "P02"
    (is (= 2 (my-last-but-one '(1 2 3))))
    (is (= 1 (my-last-but-one '(1 2))))
    (is (= nil (my-last-but-one '(1))))
    (is (= nil (my-last-but-one '())))
    (is (= nil (my-last-but-one nil)))))

(deftest p03
  (testing "P03"
    (is (= 3 (my-nth 2 '(1 2 3))))
    (is (= nil (my-nth 3 '(1 2 3))))))

(deftest p04
  (testing "P04 recursive way"
    (is (= 3 (len '(1 2 3))))
    (is (= 0 (len ()))))
  (testing "P04 map and reduce way"
    (is (= 3 (len2 '(1 2 3))))
    (is (= 0 (len2 ())))))

(deftest p05
  (testing "P05"
    (is (= '(3 2 1) (my-reverse '(1 2 3))))
    (is (= '() (my-reverse ())))))

(deftest p06
  (testing "P06"
    (is (palindrome? '(1 2 3 2 1)))
    (is (not (palindrome? '(1 2 3))))))

(deftest p07
  (testing "P07"
    (is (= '(1 2 3 4 5 6) (my-flatten [1 [2 3 [4 [5] 6]]])))))

