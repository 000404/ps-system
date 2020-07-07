/*
 * MIT License
 *
 * Copyright (c) 2020 Pasqual K. and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.derklaro.privateservers.reformcloud.v2.listeners;

import com.github.derklaro.privateservers.api.cloud.CloudSystem;
import com.github.derklaro.privateservers.reformcloud.v2.cloud.ReformCloudV2CloudService;
import org.jetbrains.annotations.NotNull;
import systems.reformcloud.reformcloud2.executor.api.event.events.process.ProcessRegisterEvent;
import systems.reformcloud.reformcloud2.executor.api.event.events.process.ProcessUnregisterEvent;
import systems.reformcloud.reformcloud2.executor.api.event.events.process.ProcessUpdateEvent;
import systems.reformcloud.reformcloud2.executor.api.event.handler.Listener;

public class CloudServiceListener {

    public CloudServiceListener(CloudSystem cloudSystem) {
        this.cloudSystem = cloudSystem;
    }

    private final CloudSystem cloudSystem;

    @Listener
    public void handleStart(@NotNull ProcessRegisterEvent event) {
        ReformCloudV2CloudService.fromProcessInformation(event.getProcessInformation()).ifPresent(
                cloudService -> this.cloudSystem.getCloudServiceManager().handleCloudServiceStart(cloudService)
        );
    }

    @Listener
    public void handleUpdate(@NotNull ProcessUpdateEvent event) {
        ReformCloudV2CloudService.fromProcessInformation(event.getProcessInformation()).ifPresent(
                cloudService -> this.cloudSystem.getCloudServiceManager().handleCloudServiceUpdate(cloudService)
        );
    }

    @Listener
    public void handleStop(@NotNull ProcessUnregisterEvent event) {
        ReformCloudV2CloudService.fromProcessInformation(event.getProcessInformation()).ifPresent(
                cloudService -> this.cloudSystem.getCloudServiceManager().handleCloudServiceStop(cloudService)
        );
    }
}
