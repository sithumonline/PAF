package com.github.paf.controller;

import com.github.paf.model.Payment;
import com.github.paf.service.PaymentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8040
 *http://localhost:8040/api/v1/payment/*
 */
@Path("/api/v1/payment")
public class PaymentController {

    private Payment payment;
    private final PaymentService paymentService = new PaymentService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(HashMap<String, ?> paymentData) {

        String month = (String) paymentData.get("month");
        String price = (String) paymentData.get("price");
        String date = (String) paymentData.get("date");
        String method = (String) paymentData.get("method");
        payment = new Payment(month, price, date, method);

        return paymentService.addPayment(payment);
    }

    @PUT
    @Path("/{paymentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePayment(HashMap<String, ?> paymentData, @PathParam("paymentId") Integer paymentId) {
        String month = (String) paymentData.get("month");
        String price = (String) paymentData.get("price");
        String date = (String) paymentData.get("date");
        String method = (String) paymentData.get("method");
        payment = new Payment(month, price, date, method);
        payment.setId(paymentId);

        return paymentService.updatePayment(payment);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayments() {
        return paymentService.getPayments();
    }

    @GET
    @Path("/{paymentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentById(@PathParam("paymentId") Integer paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @DELETE
    @Path("/{paymentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("paymentId") Integer paymentId) {
        return paymentService.deletePayment(paymentId);
    }
}
