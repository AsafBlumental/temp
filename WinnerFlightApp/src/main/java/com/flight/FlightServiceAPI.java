package com.flight;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/winner")
@Api(value = "/winner")
public interface FlightServiceAPI {

	@GET
    @Path("/check_ticket/{ticket_id}")
    @ApiOperation(value = "/check_ticket/{ticket_id}")
    public Response getIsTicketAvailable(@PathParam("ticket_id") String ticket_id);
	
	@GET
    @Path("/check_in_baggage/{dest_id}")
    @ApiOperation(value = "/check_in_baggage/{dest_id}")
    public Response getIsTicketAvailable(@PathParam("dest_id") String dest_id, @QueryParam("bag_id") String bag_id);
	
	@GET
    @Path("/check_coupon/{coupon_id}")
    @ApiOperation(value = "/check_coupon/{coupon_id}")
    public Response getPriceAfterCoupon(@PathParam("coupon_id") String coupon_id, @QueryParam("price") String price);
	
}
