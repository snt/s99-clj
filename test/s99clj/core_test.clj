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

(deftest p08
  (testing "P08"
    (is (my-compress [1 1 2 3 3 3 4 3 4 5 5 5 5 5]) [1 2 3 4 3 4 5])
    (is (my-compress '(2 2 1 1 3 1 3 1)) '(2 1 3 1 3 1))))

(deftest p09
  (testing "P09"
    (is (pack '(a a a a b c c a a d e e e e))
        '('(a a a a) '(b) '(c c) '(a a) '(d) '(e e e e)))))

(deftest p10
  (testing "P10"
    (is (rle-encode '(a a a a b c c a a d e e e e))
        '((4,a), (1,b), (2,c), (2,a), (1,d), (4,e)))))

(deftest p11
  (testing "P11"
    (is (rle-encode-modified '(a a a a b c c a a d e e e e))
        '((4,a), (1,b), (2,c), (2,a), d, (4,e)))))

(deftest p12
  (testing "P12"
    (is (rle-decode '((4,a), (1,b), (2,c), (2,a), (1,d), (4,e)))
        '(a a a a b c c a a d e e e e))))

(deftest p13
  (testing "P13"
    (is (rle-encode-direct '(a a a a b c c a a d e e e e))
        '((4,a), (1,b), (2,c), (2,a), (1,d), (4,e)))))

(deftest p14-p15
  (testing "P14-P15"
    (is (duplicate [1 2 3])
        [1 1 2 2 3 3])
    (is (duplicate 3 [1 2 3])
        [1 1 1 2 2 2 3 3 3])))

(deftest p16
  (testing "P16"
   (is (my-drop 3 [:a :b :c :d :e :f :g])
       [:a :b :d :e :g])))

(deftest p17
  (testing "P17"
          (is (my-split 3 [1 2 3 4 5 6 7])
              [[1 2 3][4 5 6 7]])))


