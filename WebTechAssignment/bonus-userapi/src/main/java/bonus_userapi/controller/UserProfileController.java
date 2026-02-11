package bonus_userapi.controller;

import bonus_userapi.UserProfile;
import bonus_userapi.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    // In-memory storage for user profiles
    private List<UserProfile> userProfiles = new ArrayList<>();
    private Long nextId = 1L;

    // Initialize with sample user profiles
    public UserProfileController() {
        userProfiles.add(new UserProfile(nextId++, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software Developer", true));
        userProfiles.add(new UserProfile(nextId++, "jane_smith", "jane@example.com", "Jane Smith", 30, "Canada", "Data Scientist", true));
        userProfiles.add(new UserProfile(nextId++, "mike_wilson", "mike@example.com", "Mike Wilson", 22, "UK", "Student", false));
        userProfiles.add(new UserProfile(nextId++, "sara_jones", "sara@example.com", "Sara Jones", 28, "Australia", "Graphic Designer", true));
        userProfiles.add(new UserProfile(nextId++, "alex_brown", "alex@example.com", "Alex Brown", 35, "Germany", "Project Manager", true));
        userProfiles.add(new UserProfile(nextId++, "lisa_chen", "lisa@example.com", "Lisa Chen", 26, "China", "UX Designer", false));
        userProfiles.add(new UserProfile(nextId++, "david_kim", "david@example.com", "David Kim", 32, "South Korea", "Mobile Developer", true));
        userProfiles.add(new UserProfile(nextId++, "maria_garcia", "maria@example.com", "Maria Garcia", 29, "Spain", "Marketing Specialist", true));
    }

    // GET /api/users - Get all user profiles
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", userProfiles));
    }

    // GET /api/users/{userId} - Get user profile by ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        for (UserProfile user : userProfiles) {
            if (user.getUserId().equals(userId)) {
                return ResponseEntity.ok(ApiResponse.success("User found", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("User not found with ID: " + userId));
    }

    // GET /api/users/search/username?username={username} - Search by username
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<UserProfile>> searchByUsername(@RequestParam String username) {
        for (UserProfile user : userProfiles) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return ResponseEntity.ok(ApiResponse.success("User found", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("User not found with username: " + username));
    }

    // GET /api/users/search/country?country={country} - Search by country
    @GetMapping("/search/country")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@RequestParam String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userProfiles) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }
        if (!result.isEmpty()) {
            return ResponseEntity.ok(ApiResponse.success("Users found in " + country, result));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("No users found in country: " + country));
    }

    // GET /api/users/search/age-range?min={min}&max={max} - Search by age range
    @GetMapping("/search/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userProfiles) {
            if (user.getAge() >= min && user.getAge() <= max) {
                result.add(user);
            }
        }
        if (!result.isEmpty()) {
            return ResponseEntity.ok(ApiResponse.success("Users found in age range " + min + "-" + max, result));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("No users found in age range " + min + "-" + max));
    }

    // GET /api/users/active - Get all active users
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getActiveUsers() {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userProfiles) {
            if (user.isActive()) {
                result.add(user);
            }
        }
        return ResponseEntity.ok(ApiResponse.success("Active users retrieved", result));
    }

    // GET /api/users/inactive - Get all inactive users
    @GetMapping("/inactive")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getInactiveUsers() {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userProfiles) {
            if (!user.isActive()) {
                result.add(user);
            }
        }
        return ResponseEntity.ok(ApiResponse.success("Inactive users retrieved", result));
    }

    // POST /api/users - Create new user profile
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile userProfile) {
        // Check if username already exists
        for (UserProfile user : userProfiles) {
            if (user.getUsername().equalsIgnoreCase(userProfile.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(ApiResponse.error("Username already exists: " + userProfile.getUsername()));
            }
        }
        
        userProfile.setUserId(nextId++);
        userProfile.setActive(true); // New users are active by default
        userProfiles.add(userProfile);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User profile created successfully", userProfile));
    }

    // PUT /api/users/{userId} - Update user profile
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(
            @PathVariable Long userId,
            @RequestBody UserProfile updatedProfile) {
        for (int i = 0; i < userProfiles.size(); i++) {
            UserProfile user = userProfiles.get(i);
            if (user.getUserId().equals(userId)) {
                // Keep the original ID and active status (unless explicitly changed)
                updatedProfile.setUserId(userId);
                if (updatedProfile.isActive() != user.isActive()) {
                    updatedProfile.setActive(updatedProfile.isActive());
                }
                userProfiles.set(i, updatedProfile);
                return ResponseEntity.ok(ApiResponse.success("User updated successfully", updatedProfile));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("User not found with ID: " + userId));
    }

    // PATCH /api/users/{userId}/activate - Activate user profile
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : userProfiles) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                return ResponseEntity.ok(ApiResponse.success("User activated successfully", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("User not found with ID: " + userId));
    }

    // PATCH /api/users/{userId}/deactivate - Deactivate user profile
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : userProfiles) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                return ResponseEntity.ok(ApiResponse.success("User deactivated successfully", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("User not found with ID: " + userId));
    }

    // DELETE /api/users/{userId} - Delete user profile
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        for (int i = 0; i < userProfiles.size(); i++) {
            if (userProfiles.get(i).getUserId().equals(userId)) {
                userProfiles.remove(i);
                return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("User not found with ID: " + userId));
    }
}