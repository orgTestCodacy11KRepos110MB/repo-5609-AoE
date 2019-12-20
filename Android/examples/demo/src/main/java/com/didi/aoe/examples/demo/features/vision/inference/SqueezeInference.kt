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

package com.didi.aoe.examples.demo.features.vision.inference

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import com.didi.aoe.extensions.support.image.AoeSupport
import com.didi.aoe.features.squeeze.SqueezeInterpreter
import com.didi.aoe.features.squeeze.extension.SqueezeModelLoaderImpl
import com.didi.aoe.library.api.domain.Device
import com.didi.aoe.library.core.AoeClient
import java.nio.ByteBuffer

/**
 *
 *
 * @author noctis
 * @since 1.1.0
 */
class SqueezeInference(context: Context, device: Device, numThreads: Int) : Inference(context, device, numThreads) {
    override fun createClient(): AoeClient {
        return AoeClient(context, "squeeze",
                AoeClient.Options()
                        .setModelOptionLoader(SqueezeModelLoaderImpl::class.java)
                        .setInterpreter(SqueezeInterpreter::class.java)
                        .setThreadNum(numThreads)
                        .useRemoteService(false),
                "squeeze")
    }

    override fun process(image: ImageProxy): Any? {
        val argb = AoeSupport.convertNV21ToARGB8888(image.planes[0].buffer.toByteArray(), image.width, image.height)
        val bp = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)
        bp.copyPixelsFromBuffer(ByteBuffer.wrap(argb))
        return aoeClient.process(Bitmap.createScaledBitmap(bp, 227, 227, false))
    }

}