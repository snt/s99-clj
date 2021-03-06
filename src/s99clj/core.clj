(ns s99clj.core)

;;; http://aperiodic.net/phil/scala/s-99/

; P01
; Clojure does not have TCO, so use `recur` instead.
; If you do not use `loop`, recursion point is `fn`.

; we cannot write "pattern matching" like this (it does not compile because two arg-body pairs have same arity.):
;(defn my-last
;  ([x] x)
;  ([[x & xs]] (recur xs)))
(defn my-last [[x & xs]]
  (if (nil? xs)
    x
    (recur xs)))


; P02
(defn my-last-but-one [[h1 & ts]]
  (let [[h2 & xs] ts]
    (if (nil? xs)
      (if (nil? h2)
        nil
        h1)
      (recur ts))))

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
      (recur (inc count)
             (rest xs)))))
; map and reduce way (pmap is parallel map)
(defn len2 [xs]
  (->> xs
       (pmap (fn [_] 1))
       (reduce +)))

;P05
; 2nd arg should be empty list instead of empty vector
;  because conj adds elment to easiest point, head for lists and tail for vectors.
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
  (loop [prev     x
         [y & ys] xs
         outs     [x]]
    (if (= y nil)
      outs
      (recur y
             ys
             (if (= prev y)
               outs
               (concat outs
                       [y]))))))

;P09
(defn pack [[x & xs]]
  (if (= nil x) []
    (loop [packed   []
           packing  [x]
           [x & xs] xs]
      (if (= nil x)
        (concat packed
                [packing])
        (if (= (first packing)
               x)
          (recur packed
                 (conj packing
                       x)
                 xs)
          (recur (concat packed
                         [packing])
                 [x]
                 xs))))))

;P10
(defn rle-encode [xs]
  (->> xs
       pack
       (map (fn [xs]
              [(len xs)
               (first xs)]))))

;P11
(defn rle-encode-modified [xs]
  (->> xs
       pack
       (map (fn [ys]
              (if (= 1 (len ys))
                (first ys)
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

;P14 & P15
(defn duplicate
  ([xs]
   (reduce (fn [left right]
             (conj left
                   right
                   right))
           []
           xs))
  ([n xs]
   (reduce (fn [left right]
             (loop [count n
                    left left
                    right right]
               (if (<= count 0)
                 left
                 (recur (dec count) (conj left right) right ))))
           []
           xs)))

;P16
(defn my-drop [n xs]
  (loop [dropped []
         index 1
         [x & xs] xs]
    (cond (nil? x)      dropped
          (= 0
             (mod index
                  n))   (recur dropped
                               (inc index)
                               xs)
          :else         (recur (conj dropped x)
                               (inc index)
                               xs))))


;P17
(defn my-split [n xs]
  (loop [heads    []
         i        n
         [x & xs] xs]
    (cond (or (nil? x)
              (<= i 0)) [heads (concat [x]
                                       xs)]
          :else         (recur (conj heads x)
                               (dec i)
                               xs))))

;P18
(defn my-slice [i k xs]
  (let [drop-first (fn [n [x & xs]]
                     (cond (<= n 1) xs
                           (nil? x) xs
                           :else    (recur (dec n)
                                           xs)))
        take-first (fn [n xs]
                     (loop [including []
                            n         n
                            [x & xs]  xs]
                       (cond (or (<= n 0)
                                 (nil? x)) including
                             :else         (recur (conj including x)
                                                  (dec n)
                                                  xs))))]
    (->> xs
         (drop-first i)
         (take-first (- k i)))))

;P19
(defn rotate [n xs]
  (->> xs
       cycle
       (drop (mod n (count xs)))
       (take (count xs))))

;P20
(defn remove-at [n xs]
  (let [[hs ts] (split-at n xs)]
    [(concat hs
             (rest ts))
     (first ts)]))

;P21
(defn insert-at [x n xs]
  (let [[hs ts] (split-at n xs)]
  [(concat hs [x] ts)]))
