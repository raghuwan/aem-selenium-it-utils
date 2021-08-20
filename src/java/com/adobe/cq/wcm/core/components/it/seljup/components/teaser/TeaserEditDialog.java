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

package com.adobe.cq.wcm.core.components.it.seljup.components.teaser;

import com.adobe.cq.testing.selenium.pagewidgets.coral.CoralCheckbox;
import com.adobe.cq.testing.selenium.pagewidgets.cq.AutoCompleteField;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TeaserEditDialog {
    private static String imageInSidePanel = "coral-card.cq-draggable[data-path='%s']";
    private static String assetUpload = ".cmp-teaser__editor coral-fileupload[name='./file']";
    private static String linkUrl = "[name='./linkURL']";
    private static String titleFromPage = ".cmp-teaser__editor input[name='./titleFromPage']";
    private static String preTitle = ".cmp-teaser__editor input[name='./pretitle']";
    private static String title = ".cmp-teaser__editor input[name='./jcr:title']";
    private static String descriptionFromPage = ".cmp-teaser__editor input[name='./descriptionFromPage']";
    private static String description = ".cmp-teaser__editor div[name='./jcr:description']";
    private static String actionsEnabled = ".cmp-teaser__editor coral-checkbox[name='./actionsEnabled']";
    private static String actionLinkURL = "[data-cmp-teaser-v1-dialog-edit-hook='actionLink']";
    private static String actionText = "[data-cmp-teaser-v1-dialog-edit-hook='actionTitle']";

    public void uploadImageFromSidePanel(String imagePath) {
        $(String.format(imageInSidePanel,imagePath)).dragAndDropTo(assetUpload);
    }

    public void setLinkURL(String url) {
        AutoCompleteField autoCompleteField = new AutoCompleteField("css:" + linkUrl);
        autoCompleteField.sendKeys(url);
        $("button[is='coral-buttonlist-item'][value='" + url + "']").click();
    }

    public void openImageTab() {
        $$(".cmp-teaser__editor coral-tab").get(0).click();
    }

    public void openTextTab() {
        $$(".cmp-teaser__editor coral-tab").get(1).click();
    }

    public void openLinkAndActionsTab() {
        $$(".cmp-teaser__editor coral-tab").get(2).click();
    }

    public void setPreTitle(String value) {
        $(preTitle).sendKeys(value);
    }

    public void setTitle(String value) {
        $(title).sendKeys(value);
    }

    public void setDescription(String value) {
        $(description).sendKeys(value);
    }

    public void clickTitleFromPage() {
        CoralCheckbox checkbox = new CoralCheckbox(titleFromPage);
        checkbox.click();
    }

    public void clickDescriptionFromPage() {
        CoralCheckbox checkbox = new CoralCheckbox(descriptionFromPage);
        checkbox.click();
    }

    public boolean isDescriptionFromPagePresent() {
        return $(descriptionFromPage).isDisplayed();
    }

    public boolean isTitleFromPagePresent() {
        return $(titleFromPage).isDisplayed();
    }

    public boolean isActionEnabledCheckDisabled() {
        return $(actionsEnabled).getAttribute("disabled").equals("true");
    }

    public boolean isActionEnabledChecked() {
        CoralCheckbox checkbox = new CoralCheckbox(actionsEnabled);
        return checkbox.isChecked();
    }

    public void clickActionEnabled() {
        CoralCheckbox checkbox = new CoralCheckbox(actionsEnabled);
        checkbox.click();
    }

    public void setActionLinkUrl(String url) {
        $$(actionLinkURL).last().find("input").sendKeys(url);
        // External Urls will not be present in suggestion
        if(url.startsWith("/content")) {
            $("button[is='coral-buttonlist-item'][value='" + url + "']").click();
        }
    }

    public void addActionLinkUrl(String url) {
        $("[coral-multifield-add]").click();
        setActionLinkUrl(url);
    }

    public void setActionText(String value) {
        $$(actionText).last().sendKeys(value);
    }

    public String getTitleValue() {
        return $(title).getValue();
    }

    public boolean isTitleEnabled() {
        return $(title).isEnabled();
    }
}
