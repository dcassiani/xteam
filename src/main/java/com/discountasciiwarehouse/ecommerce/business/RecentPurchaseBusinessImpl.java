package com.discountasciiwarehouse.ecommerce.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.discountasciiwarehouse.ecommerce.bean.PurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.discountasciiwarehouse.ecommerce.proxy.ProductProxy;
import com.discountasciiwarehouse.ecommerce.proxy.PurchaseProxy;
import com.discountasciiwarehouse.ecommerce.proxy.UserProxy;

@Service("recentPurchaseBusiness")
public class RecentPurchaseBusinessImpl implements RecentPurchaseBusiness {

	private Logger logger = Logger.getLogger(RecentPurchaseBusinessImpl.class);

	@Inject
	ProductProxy productProxy;

	@Inject
	PurchaseProxy purchaseProxy;

	@Inject
	UserProxy userProxy;

	@PostConstruct
	public void init() {
		logger.info("RecentPurchaseBusinessImpl started over CDI");
	}

	@Override
	public List<RecentPurchaseDTO> getRecentPurchaseList(UserDTO user) {

		List<RecentPurchaseDTO> recentPurchaseList = new ArrayList<RecentPurchaseDTO>();
		try {
			List<PurchaseDTO> userPurchaseList = purchaseProxy
					.getUserRecentPurchasesList(user);

			for (PurchaseDTO userPurchase : userPurchaseList) {
				RecentPurchaseDTO recentPurchase = productProxy
						.getProductInfo(userPurchase.getProductId());
				recentPurchase.setRecent(purchaseProxy
						.getProductPurchaseUsers(userPurchase.getProductId()));
				recentPurchaseList.add(recentPurchase);
			}

		} catch (Exception e) {
			logger.error("RecentPurchaseBusinessImpl.getRecentPurchaseList("
					+ user.getUsername() + ")", e);
		}

		return sortByBuyersCount(recentPurchaseList);
	}

	@Override
	public UserDTO getUser(String username) {
		return userProxy.getUser(username);
	}

	/**
	 * sort it so that the product with the highest number of recent purchases
	 * is first
	 */
	private List<RecentPurchaseDTO> sortByBuyersCount(
			List<RecentPurchaseDTO> recentPurchaseList) {

		List<RecentPurchaseDTO> sortedList = new ArrayList<RecentPurchaseDTO>();
		sortedList.addAll(recentPurchaseList);

		Comparator<RecentPurchaseDTO> comparator = new Comparator<RecentPurchaseDTO>() {
			public int compare(RecentPurchaseDTO rp1, RecentPurchaseDTO rp2) {
				return (rp2.getRecent().size() - rp1.getRecent().size());
			}
		};
		Collections.sort(sortedList, comparator);

		return sortedList;
	}

}
