package tools;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import SafeStay.dal.AddressDao;
import SafeStay.dal.AdministratorsDao;
import SafeStay.dal.ConnectionManager;
import SafeStay.dal.EndUsersDao;
import SafeStay.dal.IncidentsDao;
import SafeStay.dal.MaintainsDao;
import SafeStay.dal.MostIncidentsWeekday;
import SafeStay.dal.MostOffenseForLocation;
import SafeStay.dal.OffenseDao;
import SafeStay.dal.PercentageRequestsHighestLocations;
import SafeStay.dal.RatioReviewRecommendation;
import SafeStay.dal.RatioShootingPerLocation;
import SafeStay.dal.RecommendationsDao;
import SafeStay.dal.RequestsDao;
import SafeStay.dal.ReviewsDao;
import SafeStay.dal.SafestAreasTop20;
import SafeStay.dal.SafetyIndex;
import SafeStay.dal.UnsafeStreets;
import SafeStay.dal.UserAddressLogsDao;
import SafeStay.dal.UsersDao;
import SafeStay.model.Address;
import SafeStay.model.Administrators;
import SafeStay.model.EndUsers;
import SafeStay.model.Incidents;
import SafeStay.model.Incidents.Shootings;
import SafeStay.model.Incidents.UCRs;
import SafeStay.model.LocationRequestPercentage;
import SafeStay.model.Maintains;
import SafeStay.model.Offense;
import SafeStay.model.Recommendations;
import SafeStay.model.Requests;
import SafeStay.model.Reviews;
import SafeStay.model.ShootingLocation;
import SafeStay.model.UserAddressLogs;
import SafeStay.model.Users;

/**
 * @author Prachi Ved
 *
 */
public class Inserter {
	private static final Character INFILE_FIELD_SEPARATION_CHAR = ',';
	private static final Character INFILE_ENCLOSED_CHAR = '"';
	protected ConnectionManager connectionManager;

	protected Inserter() {
		connectionManager = new ConnectionManager();
	}

	public static void main(String[] args) throws SQLException, ParseException {

		try {

			// Test Users
			UsersDao usersDao = UsersDao.getInstance();
			Users users = new Users("prachi", "prachi", "Prachi", "Ved", 23, "ved.p@husky.neu.edu", "2384729383");
			users = usersDao.create(users);
			users = usersDao.getUserByUserName(users.getUserName());
			System.out.println("Inserted user: " + users.getUserName());

			Users user1 = new Users("sonal", "singh", "Sonal", "Singh", 23, "singh.son@husky.neu.edu", "2384729384");
			user1 = usersDao.create(user1);
			user1 = usersDao.delete(user1.getUserName());

			// Test End Users
			EndUsersDao endUsersDao = EndUsersDao.getInstance();
			String str = "1994-03-31";
			Date date = Date.valueOf(str);// converting string into sql date
			EndUsers endUsers = new EndUsers("sonal", "sonal", "Sonal", "Singh", 23, "singh.son@husky.neu.edu",
					"2384729385", date);
			endUsers = endUsersDao.create(endUsers);
			endUsers = endUsersDao.getEndUserByUserName(endUsers.getUserName());
			System.out.println("Inserted end user: " + endUsers.getUserName());

			EndUsers endUsers1 = new EndUsers("grishma", "grishma", "Grishma", "Thakkar", 23, "thakkar.g@husky.neu.edu",
					"2384729385", date);
			endUsers1 = endUsersDao.create(endUsers1);
			endUsers1 = endUsersDao.delete(endUsers1.getUserName());

			// Test Administrators
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
			Administrators administrators = new Administrators("oneil", "oneil", "ONeil", "Contracctor", 23,
					"contracctor.o@husky.neu.edu", "2384729386", timestamp);
			administrators = administratorsDao.create(administrators);
			administrators = administratorsDao.getAdministratorByUserName(administrators.getUserName());
			System.out.println("Inserted Administrator: " + administrators.getUserName());

			Administrators administrators1 = new Administrators("kartik", "kartik", "Kartik", "Dave", 23,
					"dave.k@husky.neu.edu", "2384729385", timestamp);
			administrators1 = administratorsDao.create(administrators1);
			administrators1 = administratorsDao.delete(administrators1.getUserName());

			// Test Offense
			OffenseDao offenseDao = OffenseDao.getInstance();
			offenseDao.importData("C:/Users/Prachi/Desktop/DBMS/Offense.csv", "Offense");
			Offense offense = new Offense(120, "DRUNK DRIVING");
			offense = offenseDao.create(offense);
			offense = offenseDao.getOffenseByOffenseCode(offense.getOffenceCode());
			System.out.println("Inserted Offense: " + offense.getOffenceCode());

			Offense offense1 = new Offense(119, "RASH DRIVING");
			offense1 = offenseDao.create(offense1);
			offense1 = offenseDao.delete(offense1);

			// Test Address
			AddressDao addressDao = AddressDao.getInstance();
			addressDao.importData("C:/Users/Prachi/Desktop/DBMS/Address.csv", "Address");
			Address address = new Address("(42.312007476, -71.06650934)", "Cliff Street", "42.312007476",
					"-71.06650934");
			address = addressDao.create(address);
			address = addressDao.getAddressByLocation(address.getLocation());
			System.out.println("Inserted Address: " + address.getStreet());

			Address address1 = new Address("(42.312007475, -71.06650935)", "Longwood", "42.312007475", "-71.06650935");
			address1 = addressDao.create(address1);
			address1 = addressDao.delete(address1);

			// Test Incidents
			String str2 = "2018-03-17";
			Date date2 = Date.valueOf(str2);
			IncidentsDao incidentsDao = IncidentsDao.getInstance();
			incidentsDao.importData("C:/Users/Prachi/Desktop/DBMS/NewIncidents.csv", "Incidents");
			Incidents incidents = new Incidents(offense, "Heath Street", 332, Shootings.N, date2, "Saturday", 2300,
					UCRs.PartThree, address);
			incidents = incidentsDao.create(incidents);
			System.out.println("Created incident: " + incidents.getIncidentId());

			incidents = incidentsDao.getIncidentByIncidentId(1);
			System.out.println(incidents.getIncidentId());

			List<Incidents> incidentsList = new ArrayList<>();
			incidentsList = incidentsDao.getIncidentByLocation(incidents.getAddress().getLocation());
			System.out.println("Number of Incidents at location " + incidents.getAddress().getLocation() + " is:"
					+ incidentsList.size());

			incidentsList = incidentsDao.getIncidentByOffenceCode(incidents.getOffense().getOffenceCode());
			System.out.println("Number of Incidents of Offense code " + incidents.getOffense().getOffenceCode() + " is:"
					+ incidentsList.size());

			Incidents incidents1 = new Incidents(offense, "Heath Street", 332, Shootings.Y, date2, "Saturday", 2300,
					UCRs.PartThree, address);
			incidents1 = incidentsDao.create(incidents1);
			incidents1 = incidentsDao.delete(incidents1);

			// Test Maintains
			MaintainsDao maintainsDao = MaintainsDao.getInstance();
			Maintains maintains = new Maintains(administrators, incidents, timestamp);
			maintains = maintainsDao.create(maintains);
			List<Maintains> maintainsList = new ArrayList<>();
			maintainsList = maintainsDao.getMaintainRecordByUserName(maintains.getAdministrators().getUserName());
			System.out.println(maintainsList.size());
			maintains = maintainsDao.delete(maintains);

			// Test Reviews
			ReviewsDao reviewsDao = ReviewsDao.getInstance();
			Reviews reviews = new Reviews("Safe area", endUsers, address);
			reviews = reviewsDao.create(reviews);
			List<Reviews> reviewsList = new ArrayList<>();
			reviews = reviewsDao.getReviewById(1);
			System.out.println("Reviews with review id 1: " + reviews.getContent());
			reviewsList = reviewsDao.getReviewsBylocation(reviews.getAddress().getLocation());
			System.out.println(reviewsList.size());
			reviewsList = reviewsDao.getReviewsByUserName(reviews.getEndusers().getUserName());
			System.out.println(reviewsList.size());
			reviews = reviewsDao.delete(reviews.getReviewId());

			// Test Recommendations
			RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
			Recommendations recommendations = new Recommendations(3.0, 3.0, 3.0, endUsers, address);
			recommendations = recommendationsDao.create(recommendations);
			List<Recommendations> recommendationsList = new ArrayList<>();
			recommendations = recommendationsDao.getRecommendationById(1);
			System.out.println("Recommendations with id 1: " + recommendations.getRating());
			recommendations = recommendationsDao.updateRating(recommendations, 4.0);
			System.out.println("Updated rating is: " + recommendations.getRating());
			recommendationsList = recommendationsDao
					.getRecommendationsByLocation(recommendations.getAddress().getLocation());
			System.out.println(recommendationsList.size());
			recommendationsList = recommendationsDao
					.getRecommendationsByUserName(recommendations.getEndusers().getUserName());
			System.out.println(recommendationsList.size());
			recommendations = recommendationsDao.delete(recommendations.getRecommendationId());

			// Test Requests
			RequestsDao requestsDao = RequestsDao.getInstance();
			Requests requests = new Requests(endUsers, address);
			requests = requestsDao.create(requests);
			List<Requests> requestsList = new ArrayList<>();
			requests = requestsDao.getRequestById(1);
			System.out.println("Requests with id 1: " + requests.getRequestId());
			requestsList = requestsDao.getRequestsByLocation(requests.getAddress().getLocation());
			System.out.println(requestsList.size());
			// requests = requestsDao.delete(requests);

			// Test UserAddressLogs
			UserAddressLogsDao userAddressLogsDao = UserAddressLogsDao.getInstance();
			UserAddressLogs userAddressLogs = new UserAddressLogs(endUsers, address, date, date2);
			userAddressLogs = userAddressLogsDao.create(userAddressLogs);
			List<UserAddressLogs> userAddressLogsList = new ArrayList<>();
			userAddressLogs = userAddressLogsDao.getUALogsByUserAddressId(1);
			System.out.println("Logs with id 1: " + userAddressLogs.getUserAddressId());
			userAddressLogsList = userAddressLogsDao.getUALogsByLocation(userAddressLogs.getAddress().getLocation());
			System.out.println(userAddressLogsList.size());
			userAddressLogsList = userAddressLogsDao.getUALogsByUserName(userAddressLogs.getEndUser().getUserName());
			System.out.println(userAddressLogsList.size());
			userAddressLogs = userAddressLogsDao.delete(userAddressLogs);

			// Test location request
			System.out.println("Locations with most requests and their request count along with the percentage");
			PercentageRequestsHighestLocations perReq = PercentageRequestsHighestLocations.getInstance();
			for (LocationRequestPercentage s : perReq.getMostRequestedLocations()) {

				System.out.println(s.getLocStreet());
				System.out.println(s.getHighRequest());
				System.out.println(s.getTotalRequest());
				System.out.println(s.getPercentage());

			}

//			// Test Weekday with most incidents - query 5
//			System.out.println("Weekday with most incidents is : ");
//			MostIncidentsWeekday mostday = MostIncidentsWeekday.getInstance();
//			System.out.println(mostday.getWeekdayMostIncidents());
//
//			// Test most offense for location - query2
//
//			System.out.println("Most offense in the location : ");
//			MostOffenseForLocation mostOffense = MostOffenseForLocation.getInstance();
//			System.out.println(mostOffense.getOffenseCodeForLocation());
//
//			// Test Shooting Percentages - query 6
//			System.out.println("Ratio of shooting incidents to the overall incidents that occur in a system");
//			RatioShootingPerLocation ratioshooting = RatioShootingPerLocation.getInstance();
//
//			System.out.println(ratioshooting.getRatioshootingIncidents().size());
//			for (ShootingLocation s : ratioshooting.getRatioshootingIncidents()) {
//				// System.out.println(x);
//				System.out.println(s.getLocation() + " : " + s.getRatio());
//			}
//
//			// Test RatioReviewRecommendations
//			Reviews reviews2 = new Reviews("Safe area", endUsers, address);
//			reviews2 = reviewsDao.create(reviews2);
//			Recommendations recommendations2 = new Recommendations(3.0, 3.0, 3.0, endUsers, address);
//			recommendations2 = recommendationsDao.create(recommendations2);
//			RatioReviewRecommendation k = RatioReviewRecommendation.getInstance();
//			System.out.println("The ratio of of total number of recommendations to reviews for Boston are: "
//					+ k.getRatioOfRecommendationAndReview());
//
//			// Test top 20 safest Locations to live:
//			System.out.println("Top 20 safe locations are:");
//			SafestAreasTop20 saf = SafestAreasTop20.getInstance();
//			int kk = 1;
//			for (String m : saf.getTop20SafestAreas()) {
//				System.out.println(kk + ": " + m);
//				++kk;
//			}
//
//			// Test top 20 unsafe Streets to live:
//			System.out.println("Top 20 unsafe streets are:");
//			UnsafeStreets unsa = UnsafeStreets.getInstance();
//			int kkm = 1;
//			for (String m : unsa.getUnsafeStreets()) {
//				System.out.println(kkm + ": " + m);
//				++kkm;
//			}
//
//			// Test safety index of all the streets
//			System.out.println("Safety index of streets are:");
//			SafetyIndex si = SafetyIndex.getInstance();
//			int kkmm = 1;
//			for (String m : si.getSafetyIndex()) {
//				System.out.println(kkmm + ": " + m);
//				++kkm;
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
