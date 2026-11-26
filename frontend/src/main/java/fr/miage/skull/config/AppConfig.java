package fr.miage.skull.config;

import fr.miage.skull.models.User;
import fr.miage.skull.services.AuthService;
import fr.miage.skull.services.mock.MockAuthService;

public final class AppConfig {
    public static final AuthService AUTH = new MockAuthService();
    public static final fr.miage.skull.services.PlayerService PLAYERS = new fr.miage.skull.services.mock.MockPlayerService();
    public static final fr.miage.skull.services.HistoryService HISTORY = new fr.miage.skull.services.mock.MockHistoryService();
    public static final boolean API_MODE = "api".equalsIgnoreCase(System.getenv("SKULL_MODE"));
    public static final fr.miage.skull.services.api.ApiClient HTTP = new fr.miage.skull.services.api.ApiClient(System.getenv().getOrDefault("SKULL_API_URL", "http://localhost:8080"));
    public static final fr.miage.skull.services.MarketService MARKET = API_MODE ? new fr.miage.skull.services.api.ApiMarketService(HTTP) : new fr.miage.skull.services.mock.MockMarketService();
    public static final fr.miage.skull.services.GameService GAME = API_MODE ? new fr.miage.skull.services.api.ApiGameService(HTTP) : new fr.miage.skull.services.mock.MockGameService();

    public static String pseudo() {
        return API_MODE ? System.getenv().getOrDefault("SKULL_API_PSEUDO", "") : user.name();
    }

    public static String marketIdentity() {
        return API_MODE ? pseudo() : user.email();
    }

    private static User user;

    public static User user() {
        return user;
    }

    public static void session(User u) {
        user = u;
    }
}
