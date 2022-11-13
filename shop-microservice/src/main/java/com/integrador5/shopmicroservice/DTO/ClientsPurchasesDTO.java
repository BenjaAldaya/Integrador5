package com.integrador5.shopmicroservice.DTO;
/**
 *
 * @author Grupo1
 * @version Unique Version
 * @category DTO
 *
 */
public class ClientsPurchasesDTO {
    private String clientName;
    private float totalPrice;

    public ClientsPurchasesDTO(){}

    public ClientsPurchasesDTO(String clientName, float totalPrice) {
        this.clientName = clientName;
        this.totalPrice = totalPrice;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "clientsPurchasesDTO{" +
                "clientName='" + clientName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
