(ns s99clj.core)

;;; http://aperiodic.net/phil/scala/s-99/

; P01
; Clojure does not have TCO, so use `recur` instead.
; If you do not use `loop`, recursion point is `fn`.

(defn my-last [xs]
  (let [h (first xs)
        ts (rest xs)]
    (if (= '() ts)
      h
      (recur ts))))

; P02
(defn my-last-but-one [xs]
  (let [h1 (first xs)
        h2 (first (rest xs))
        ts (rest (rest xs))]
    (if (= '() ts)
      (if-not (nil? h2) h1 nil)
      (recur (rest xs)))))

;P03
(defn my-nth [n xs]
  (if (= n 0)
    (first xs)
    (recur (dec n) (rest xs))))

