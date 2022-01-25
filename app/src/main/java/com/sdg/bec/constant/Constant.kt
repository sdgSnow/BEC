package com.sdg.bec.constant

import android.os.Environment

interface Constant {

    interface Status{
        companion object{
            //新增
            const val add = 0
            //熟悉
            const val know = 1
            //熟记
            const val memorize = 2
        }
    }

    interface Important{
        companion object{
            //初级
            const val primary = 0
            //中级
            const val middle = 1
            //高级
            const val high = 2
        }
    }

    /**
     * 单词类型
     * */
    interface WordsClassify{
        companion object{
            const val fruit = 1
        }
    }

    /**
     * 精句类型
     * */
    interface SentenceClassify{
        companion object{
            //生活相关
            const val life = 1
            //精美句子
            const val fineSentence = 2
            //短语
            const val phrase = 3
        }
    }

    /**
     * 文章类型
     * */
    interface ArticleClassify{
        companion object{
            const val life = 1
        }
    }
}