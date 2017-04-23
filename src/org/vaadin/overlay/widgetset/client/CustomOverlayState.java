/*
 * Copyright 2010 Sami Ekblad, 2013 Haulmont Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.overlay.widgetset.client;

import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AbstractComponentContainerState;
import com.vaadin.shared.ui.AlignmentInfo;

/**
 * @author nevinsky
 */
public class CustomOverlayState extends AbstractComponentContainerState {
    public int x = 0;
    public int y = 0;
    public int alignBitMask = AlignmentInfo.TOP_LEFT.getBitMask();
    public int overlayAlignBitMask = AlignmentInfo.TOP_LEFT.getBitMask();

    public Connector component;
}
