package com.flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightServiceImp implements FlightServiceAPI {

	@Autowired
    private IServiceManagerService serviceManagerService;
	
	private static Logger log = LoggerFactory.getLogger(FlightServiceImp.class);
	
	private static List<Integer> availableTickets = new ArrayList<Integer>();
	private static Map<Integer, String> successfullCheckIn = new HashMap<Integer, String>();
	private static Map<Integer, Integer> couponToDiscountPercentage = new HashMap<Integer, Integer>();
	
	public FlightServiceImp(){
		super();
	}
	
	public void init(){
		
        this.serviceManagerService.createRestWebService(this, null);
		
		availableTickets.add(1234);
		availableTickets.add(1);
		availableTickets.add(55);
		availableTickets.add(144);
		availableTickets.add(888);
		
		successfullCheckIn.put(123, "abc");
		successfullCheckIn.put(1235, "abc88");
		successfullCheckIn.put(12366, "ab6c");
		
		couponToDiscountPercentage.put(1000, 10);
		couponToDiscountPercentage.put(10450, 50);
		couponToDiscountPercentage.put(9999, 60);
	}
	
	@Override
	public Response getIsTicketAvailable(String ticket_id) {
		if(ticket_id == null){
			return null;
		}
		
		Integer ticketNum = Integer.decode(ticket_id);
        
		return handleResult(availableTickets.contains(ticketNum));
	}

	@Override
	public Response getIsTicketAvailable(String dest_id, String bag_id) {
		if(dest_id == null || bag_id == null){
			return null;
		}
		
		Integer destIdNum = Integer.decode(dest_id);
		
		return handleResult(successfullCheckIn.get(destIdNum).equals(bag_id));
			
	}
	
	@Override
	public Response getPriceAfterCoupon(String coupon_id, String price) {
		if(coupon_id == null || price == null){
			return null;
		}
		
		Integer couponIdNum = Integer.decode(coupon_id);
		Integer priceBeforeDisc = Integer.decode(price);
		
		Integer discountPerc = couponToDiscountPercentage.get(couponIdNum);
		if(discountPerc != null){
			log.debug("user entered valid coupon " + coupon_id + " and got " + discountPerc + "% discount");
			return handleResult(priceBeforeDisc * discountPerc / 100);
		}
		else{
			log.debug("user entered invalid coupon " + coupon_id);
			return null;
		}
	}
	
	 protected Response handleResult(Object entity){
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        responseBuilder.type(MediaType.APPLICATION_JSON);
        responseBuilder.entity(entity);
        return responseBuilder.build();
    }

}
