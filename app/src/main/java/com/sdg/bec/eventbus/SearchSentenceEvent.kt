package com.sdg.bec.eventbus

import com.sdg.bec.db.Sentence
import org.litepal.FluentQuery

class SearchSentenceEvent(var list: List<Sentence>)