/*
 * Copyright 2019 The AoE Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.didi.aoe.library.common.stat

/**
 *
 *
 * @author noctis
 * @since 1.1.0
 */
class TimeStat {

    companion object {
        private val startTimeCache = mutableMapOf<String, Long>()
        private val endTimeCache = mutableMapOf<String, Long>()

        @JvmStatic
        fun setStart(key: String, time: Long) {
            startTimeCache[key] = time
        }

        @JvmStatic
        fun setEnd(key: String, time: Long) {
            endTimeCache[key] = time
        }

        @JvmStatic
        fun getCostTime(key: String): Long {
            return (endTimeCache[key] ?: 0) - (startTimeCache[key] ?: 0)
        }
    }
}