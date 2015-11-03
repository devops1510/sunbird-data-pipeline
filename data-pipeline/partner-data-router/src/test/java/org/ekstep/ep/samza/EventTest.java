package org.ekstep.ep.samza;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

public class EventTest {
    @Test
    public void shouldBelongToPartnerIfPartnerIdIsPresent() {
        HashMap<String, Object> data = new HashMap<String,Object>();
        HashMap<String, Object> edata = new HashMap<String,Object>();
        HashMap<String, Object> eks = new HashMap<String,Object>();
        data.put("edata", edata);
        edata.put("eks", eks);
        eks.put("partnerid","org.test.partner.id");
        Event event = new Event(data);

        Assert.assertTrue(event.belongsToAPartner());
    }

    @Test
    public void shouldNotBelongToPartnerIfPartnerIdIsAbsent() {
        HashMap<String, Object> data = new HashMap<String,Object>();
        HashMap<String, Object> edata = new HashMap<String,Object>();
        HashMap<String, Object> eks = new HashMap<String,Object>();
        data.put("edata", edata);
        edata.put("eks", eks);
        Event event = new Event(data);

        Assert.assertFalse(event.belongsToAPartner());
    }

    @Test
    public void shouldNotBelongToPartnerIfEksIsAbsent() {
        HashMap<String, Object> data = new HashMap<String,Object>();
        HashMap<String, Object> edata = new HashMap<String,Object>();
        data.put("edata", edata);
        Event event = new Event(data);

        Assert.assertFalse(event.belongsToAPartner());
    }

    @Test
    public void shouldNotBelongToPartnerIfEdataIsAbsent() {
        HashMap<String, Object> data = new HashMap<String,Object>();
        Event event = new Event(data);

        Assert.assertFalse(event.belongsToAPartner());
    }

    @Test
    public void shouldRouteToTheEventsTopicBasedOnPartnerId() {
        HashMap<String, Object> data = new HashMap<String,Object>();
        HashMap<String, Object> edata = new HashMap<String,Object>();
        HashMap<String, Object> eks = new HashMap<String,Object>();
        data.put("edata", edata);
        edata.put("eks", eks);
        eks.put("partnerid","org.test.partner.id");
        Event event = new Event(data);

        Assert.assertEquals("org.test.partner.id.events",event.routeTo());
    }

    @Test
    public void shouldGetDataFromEvent() {
        HashMap<String, Object> data = new HashMap<String,Object>();
        HashMap<String, Object> edata = new HashMap<String,Object>();
        HashMap<String, Object> eks = new HashMap<String,Object>();
        data.put("edata", edata);
        edata.put("eks", eks);
        eks.put("partnerid","org.test.partner.id");
        Event event = new Event(data);

        Assert.assertEquals(data,event.getData());
    }

}