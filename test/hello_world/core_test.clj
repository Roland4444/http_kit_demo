(ns hello-world.core-test
  (:require [clojure.test :refer :all]
            [hello-world.core :refer :all]
            [hello-world.views.index :refer :all]
            ))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest s-test
  (testing "XXX"
  (is (= 3 (int(sum2 1 2))))
  (is (= 0 (int(sum2 ))))
  (is (= 12 (int(sum2 12))))
  )
)    

(comment
(deftest sum-test
  (testing "sumtest"
   (is (= (int (sum2 (1 2))) 3))))
)
