/*
 * Swagger Petstore - OpenAPI 3.0
 * This is a sample Pet Store Server based on the OpenAPI 3.0 specification.  You can find out more about Swagger at [http://swagger.io](http://swagger.io). In the third iteration of the pet store, we've switched to the design first approach! You can now help us improve the API whether it's by making changes to the definition itself or to the code. That way, with time, we can improve the API in general, and expose some of the new features in OAS3.  Some useful links: - [The Pet Store repository](https://github.com/swagger-api/swagger-petstore) - [The source API definition for the Pet Store](https://github.com/swagger-api/swagger-petstore/blob/master/src/main/resources/openapi.yaml)
 *
 * The version of the OpenAPI document: 1.0.9
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.paloit.client.petstore.webclient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Order
 */
@JsonPropertyOrder({
  Order.JSON_PROPERTY_ID,
  Order.JSON_PROPERTY_PET_ID,
  Order.JSON_PROPERTY_QUANTITY,
  Order.JSON_PROPERTY_SHIP_DATE,
  Order.JSON_PROPERTY_STATUS,
  Order.JSON_PROPERTY_COMPLETE
})
@JsonTypeName("Order")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-01-19T15:22:07.236567+07:00[Asia/Bangkok]")
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private Long id;

  public static final String JSON_PROPERTY_PET_ID = "petId";
  private Long petId;

  public static final String JSON_PROPERTY_QUANTITY = "quantity";
  private Integer quantity;

  public static final String JSON_PROPERTY_SHIP_DATE = "shipDate";
  private OffsetDateTime shipDate;

  /**
   * Order Status
   */
  public enum StatusEnum {
    PLACED("placed"),
    
    APPROVED("approved"),
    
    DELIVERED("delivered");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_STATUS = "status";
  private StatusEnum status;

  public static final String JSON_PROPERTY_COMPLETE = "complete";
  private Boolean complete;

  public Order() { 
  }

  public Order id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "10", value = "")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setId(Long id) {
    this.id = id;
  }


  public Order petId(Long petId) {
    
    this.petId = petId;
    return this;
  }

   /**
   * Get petId
   * @return petId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "198772", value = "")
  @JsonProperty(JSON_PROPERTY_PET_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getPetId() {
    return petId;
  }


  @JsonProperty(JSON_PROPERTY_PET_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPetId(Long petId) {
    this.petId = petId;
  }


  public Order quantity(Integer quantity) {
    
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "7", value = "")
  @JsonProperty(JSON_PROPERTY_QUANTITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getQuantity() {
    return quantity;
  }


  @JsonProperty(JSON_PROPERTY_QUANTITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  public Order shipDate(OffsetDateTime shipDate) {
    
    this.shipDate = shipDate;
    return this;
  }

   /**
   * Get shipDate
   * @return shipDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SHIP_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OffsetDateTime getShipDate() {
    return shipDate;
  }


  @JsonProperty(JSON_PROPERTY_SHIP_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setShipDate(OffsetDateTime shipDate) {
    this.shipDate = shipDate;
  }


  public Order status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * Order Status
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "approved", value = "Order Status")
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public StatusEnum getStatus() {
    return status;
  }


  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public Order complete(Boolean complete) {
    
    this.complete = complete;
    return this;
  }

   /**
   * Get complete
   * @return complete
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COMPLETE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getComplete() {
    return complete;
  }


  @JsonProperty(JSON_PROPERTY_COMPLETE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setComplete(Boolean complete) {
    this.complete = complete;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.petId, order.petId) &&
        Objects.equals(this.quantity, order.quantity) &&
        Objects.equals(this.shipDate, order.shipDate) &&
        Objects.equals(this.status, order.status) &&
        Objects.equals(this.complete, order.complete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, petId, quantity, shipDate, status, complete);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    petId: ").append(toIndentedString(petId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    shipDate: ").append(toIndentedString(shipDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
