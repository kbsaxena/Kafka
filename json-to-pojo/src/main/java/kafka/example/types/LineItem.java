
package kafka.example.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ItemCode",
    "ItemDescription",
    "ItemPrice",
    "ItemQty",
    "ItemValue"
})
public class LineItem {

    @JsonProperty("ItemCode")
    private String itemCode;
    @JsonProperty("ItemDescription")
    private String itemDescription;
    @JsonProperty("ItemPrice")
    private Double itemPrice;
    @JsonProperty("ItemQty")
    private Integer itemQty;
    @JsonProperty("ItemValue")
    private Double itemValue;

    @JsonProperty("ItemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("ItemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public LineItem withItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    @JsonProperty("ItemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("ItemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public LineItem withItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    @JsonProperty("ItemPrice")
    public Double getItemPrice() {
        return itemPrice;
    }

    @JsonProperty("ItemPrice")
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public LineItem withItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    @JsonProperty("ItemQty")
    public Integer getItemQty() {
        return itemQty;
    }

    @JsonProperty("ItemQty")
    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public LineItem withItemQty(Integer itemQty) {
        this.itemQty = itemQty;
        return this;
    }

    @JsonProperty("ItemValue")
    public Double getItemValue() {
        return itemValue;
    }

    @JsonProperty("ItemValue")
    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public LineItem withItemValue(Double itemValue) {
        this.itemValue = itemValue;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("itemCode", itemCode).append("itemDescription", itemDescription).append("itemPrice", itemPrice).append("itemQty", itemQty).append("itemValue", itemValue).toString();
    }

}
