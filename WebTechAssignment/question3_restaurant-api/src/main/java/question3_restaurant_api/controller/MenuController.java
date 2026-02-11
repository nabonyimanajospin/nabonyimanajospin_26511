package question3_restaurant_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import question3_restaurant_api.MenuItem;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    // In-memory storage for menu items
    private List<MenuItem> menuItems = new ArrayList<>();
    private Long nextId = 1L;

    // Initialize with 8 sample menu items across all categories
    public MenuController() {
        // Appetizers
        menuItems.add(new MenuItem(nextId++, "Garlic Bread", "Toasted bread with garlic butter", 4.99, "Appetizer", true));
        menuItems.add(new MenuItem(nextId++, "Bruschetta", "Grilled bread topped with tomatoes and basil", 5.99, "Appetizer", true));
        
        // Main Courses
        menuItems.add(new MenuItem(nextId++, "Spaghetti Carbonara", "Pasta with creamy egg and bacon sauce", 12.99, "Main Course", true));
        menuItems.add(new MenuItem(nextId++, "Grilled Salmon", "Fresh salmon with lemon butter sauce", 18.99, "Main Course", false)); // Not available
        menuItems.add(new MenuItem(nextId++, "Chicken Alfredo", "Fettuccine with creamy Alfredo sauce", 14.99, "Main Course", true));
        
        // Desserts
        menuItems.add(new MenuItem(nextId++, "Chocolate Cake", "Rich chocolate cake with ganache", 6.99, "Dessert", true));
        menuItems.add(new MenuItem(nextId++, "Tiramisu", "Classic Italian coffee-flavored dessert", 7.99, "Dessert", true));
        
        // Beverages
        menuItems.add(new MenuItem(nextId++, "Fresh Lemonade", "Homemade lemonade with mint", 3.99, "Beverage", true));
    }

    // GET /api/menu - Get all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // GET /api/menu/{id} - Get specific menu item
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /api/menu/category/{category} - Get items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET /api/menu/available - Get only available items
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(@RequestParam(defaultValue = "true") boolean available) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET /api/menu/search?name={name} - Search menu items by name
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST /api/menu - Add new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        menuItems.add(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    // PUT /api/menu/{id}/availability - Toggle item availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE /api/menu/{id} - Remove menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                menuItems.remove(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}