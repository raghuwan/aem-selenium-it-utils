/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2021 Adobe
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package com.adobe.cq.wcm.core.components.it.seljup.components.teaser.v1;

import com.adobe.cq.testing.selenium.pagewidgets.common.BaseComponent;
import com.adobe.cq.wcm.core.components.it.seljup.components.teaser.TeaserEditDialog;
import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Teaser extends BaseComponent {
    private static String teaser = ".cmp-teaser";
    private static String teaserImage = ".cmp-teaser__image";
    private static String TeaserPreTitle = ".cmp-teaser__pretitle";
    private static String teaserTitle = ".cmp-teaser__title";
    private static String teaserTitleLink = ".cmp-teaser__title-link";
    private static String teaserDescription = ".cmp-teaser__description";
    private static String teaserActionLink = "a.cmp-teaser__action-link";
    private static String imageTag = teaserImage + " img[src*='%s/_jcr_content/root/responsivegrid/teaser']";

    public Teaser() {
        super(teaser);
    }

    public TeaserEditDialog getEditDialog() {
        return new TeaserEditDialog();
    }

    public boolean isImagePresent(String path) {
        return $(String.format(imageTag, path)).isDisplayed();
    }

    public boolean isPreTitlePresent(String preTitle) {
        return $(TeaserPreTitle).innerHtml().trim().equals(preTitle);
    }

    public boolean isTitleLinkPresent(String path, String title) {
        if($("a" + teaserTitleLink + "[href$='" + path + ".html']" ).isDisplayed()) {
            return $("a" + teaserTitleLink + "[href$='" + path + ".html']").getText().trim().equals(title);
        }
        return false;
    }

    public boolean isTitleLinkPresent() {
        return $(teaserTitleLink).isDisplayed();
    }

    public boolean isDescriptionPresent(String description) {
        return $(teaserDescription).getText().equals(description);
    }

    public boolean isDescriptionPresent() {
        return $(teaserDescription).isDisplayed();
    }

    public boolean isTitlePresent(String title) {
        return $(teaserTitle).getText().equals(title);
    }

    public boolean isTitleHidden() {
        return !$(teaserImage + " a").isDisplayed();
    }

    public boolean isActionLinkPresent(String url) {
        ElementsCollection items = $$(teaserActionLink);
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getText().contains(url)) {
                return true;
            }
        }
        return false;
    }
}
