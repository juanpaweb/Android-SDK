package it.near.sdk.reactions.coupon;

import android.os.Parcel;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import it.near.sdk.BuildConfig;
import it.near.sdk.reactions.content.ImageSet;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CouponTest {

    @Test
    public void couponIsParcelable() {
        Coupon coupon = new Coupon();
        coupon.setId("coupon_id");
        coupon.setName("coupon_name");
        coupon.setDescription("coupon_description");
        coupon.setValue("coupon_value");
        coupon.setExpires_at("expiring_soon");
        coupon.setIcon_id("coupon_icon_id");
        coupon.setClaims(Lists.newArrayList(new Claim(), new Claim()));
        coupon.setIconSet(new ImageSet());

        Parcel parcel = Parcel.obtain();
        coupon.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Coupon actual = Coupon.CREATOR.createFromParcel(parcel);

        assertThat(actual.getId(), is(coupon.getId()));
        assertThat(actual.getName(), is(coupon.getName()));
        assertThat(actual.getDescription(), is(coupon.getDescription()));
        assertThat(actual.getValue(), is(coupon.getValue()));
        assertThat(actual.getExpires_at(), is(coupon.getExpires_at()));
        assertThat(actual.getIcon_id(), is(coupon.getIcon_id()));
        assertThat(coupon.getClaims(), containsInAnyOrder(actual.getClaims().toArray()));
        assertThat(actual.getIconSet(), is(coupon.getIconSet()));
    }
}