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

;P04
; recursive way
(defn len [xs]
  (loop [count 0
         xs xs]
    (if (= '() xs)
      count
      (recur (inc count) (rest xs)))))
; map and reduce way
(defn len2 [xs]
  (->> xs
       (map (fn [_] 1))
       (reduce +)))

;P05
(defn my-reverse [xs]
      (reduce conj () xs))

;P06
(defn palindrome? [xs]
  (= (my-reverse xs) xs))

;P07
(defn my-flatten [xs]
  (reduce
   #(concat
     %1
     (if (coll? %2)
       (my-flatten %2)
       [%2]))
   []
   xs))
