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

;P08
(defn my-compress [[x & xs]]
  (loop [prev x
         [y & ys] xs
         outs [x]]
    (if (= y nil)
      outs
      (recur y
             ys
             (if (= prev y)
               outs
               (concat outs [y]))))))

;P09
(defn pack [[x & xs]]
  (if (= nil x) []
    (loop [packed   []
           packing  [x]
           [x & xs] xs]
      (if (= nil x)
        (concat packed [packing])
        (if (= (first packing) x)
          (recur packed
                 (conj packing x)
                 xs)
          (recur (concat packed [packing])
                 [x]
                 xs))))))

;P10
(defn rle-encode [xs]
  (->> xs
       pack
       (map (fn [xs] [(len xs) (first xs)] ))))

;P11
(defn rle-encode-modified [xs]
  (->> xs
       pack
       (map (fn [ys]
              (case (len ys)
                1 (first ys)
                [(len ys) (first ys)])))))

;P12
(defn rle-decode [xs]
  (reduce concat
          (map (fn [[count item]]
                 (loop [expanded []
                        count count
                        item item]
                   (if (<= count 0)
                     expanded
                     (recur (concat expanded [item])
                            (dec count)
                            item))))
               xs)))

;P13
(defn rle-encode-direct [[x & xs]]
  (loop [encoded  []
         run      [1 x]
         [x & xs] xs]
    (cond (nil? x)      (conj encoded
                              run)
          (= (run 1) x) (recur encoded
                               [(inc (run 0))
                                (run 1)]
                               xs)
          :else         (recur (conj encoded
                                     run)
                               [1 x]
                               xs))))
