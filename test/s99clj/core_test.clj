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
