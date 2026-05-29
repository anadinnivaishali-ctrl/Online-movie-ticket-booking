package com.jsp.book.service;

import com.jsp.book.dto.*;
import com.jsp.book.entity.Movie;
import com.jsp.book.entity.User;
import com.jsp.book.repository.MovieRepository;
import com.jsp.book.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.ModelMap;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(MovieRepository movieRepository,
                           UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String register(UserDto userDto, BindingResult result, RedirectAttributes attributes) {
        return "redirect:/";
    }

    @Override
    public String login(LoginDto dto, RedirectAttributes ra, HttpSession session) {
        Optional<User> optional = userRepository.findByEmail(dto.getEmail());

        // ❌ User not found
        if (optional.isEmpty()) {
            ra.addFlashAttribute("fail", "Invalid email or password!");
            return "redirect:/login";
        }

        User user = optional.get();

        // ❌ Wrong password
        if (!user.getPassword().equals(dto.getPassword())) {
            ra.addFlashAttribute("fail", "Invalid email or password!");
            return "redirect:/login";
        }

        // ❌ Blocked user
        if (user.isBlocked()) {
            ra.addFlashAttribute("fail", "Your account has been blocked!");
            return "redirect:/login";
        }

        // ✅ Save user in session
        session.setAttribute("user", user);

        // ✅ Redirect based on role
        if (user.getRole().equals("ADMIN")) {
            ra.addFlashAttribute("pass", "Welcome Admin " + user.getName() + "!");
            return "redirect:/manage-movies";
        } else {
            ra.addFlashAttribute("pass", "Welcome " + user.getName() + "!");
            return "redirect:/";
        }
    }

    @Override
    public String logout(HttpSession session, RedirectAttributes ra) {
        session.invalidate();
        ra.addFlashAttribute("pass", "Logged out successfully!");
        return "redirect:/login";
    }

    @Override
    public String loadMain(ModelMap map) {
        List<Movie> movies = movieRepository.findAll();
        map.addAttribute("movies", movies != null ? movies : Collections.emptyList());
        return "main";
    }

    @Override
    public String submitOtp(int id, String otp, RedirectAttributes ra) {
        return "redirect:/login";
    }

    @Override
    public String resendOtp(String email, RedirectAttributes ra) {
        return "redirect:/otp";
    }

    @Override
    public String forgotPassword(String email, RedirectAttributes ra) {
        return "redirect:/forgot";
    }

    @Override
    public String resetPassword(PasswordDto dto, BindingResult br,
                                RedirectAttributes ra, ModelMap map) {
        return "redirect:/login";
    }

    @Override
    public String manageUsers(HttpSession session, RedirectAttributes ra, ModelMap map) {
        return "admin/users";
    }

    @Override
    public String blockUser(Long id, HttpSession session, RedirectAttributes ra) {
        return "redirect:/users";
    }

    @Override
    public String unBlockUser(Long id, HttpSession session, RedirectAttributes ra) {
        return "redirect:/users";
    }

    @Override
    public String manageTheater(ModelMap map, RedirectAttributes ra, HttpSession session) {
        return "admin/theater";
    }

    @Override
    public String loadAddTheater(HttpSession session, RedirectAttributes ra, TheaterDto dto) {
        return "admin/add-theater";
    }

    @Override
    public String addTheater(HttpSession session, RedirectAttributes ra,
                             TheaterDto dto, BindingResult br) {
        return "redirect:/theater";
    }

    @Override
    public String deleteTheater(Long id, HttpSession session, RedirectAttributes ra) {
        return "redirect:/theater";
    }

    @Override
    public String editTheater(Long id, HttpSession session,
                              RedirectAttributes ra, ModelMap map) {
        return "admin/edit-theater";
    }

    @Override
    public String updateTheater(HttpSession session, RedirectAttributes ra,
                                TheaterDto dto, BindingResult br, Long id) {
        return "redirect:/theater";
    }

    @Override
    public String manageScreens(Long theaterId, HttpSession session,
                                RedirectAttributes ra, ModelMap map) {
        return "admin/screens";
    }

    @Override
    public String addScreen(Long theaterId, HttpSession session,
                            RedirectAttributes ra, ModelMap map, ScreenDto dto) {
        return "admin/add-screen";
    }

    @Override
    public String addScreen(ScreenDto dto, BindingResult br,
                            HttpSession session, RedirectAttributes ra) {
        return "redirect:/screens";
    }

    @Override
    public String deleteScreen(Long id, HttpSession session, RedirectAttributes ra) {
        return "redirect:/screens";
    }

    @Override
    public String editScreen(Long id, HttpSession session,
                             RedirectAttributes ra, ModelMap map) {
        return "admin/edit-screen";
    }

    @Override
    public String updateScreen(ScreenDto dto, Long id, BindingResult br,
                               HttpSession session, RedirectAttributes ra, ModelMap map) {
        return "redirect:/screens";
    }

    @Override
    public String manageSeats(Long screenId, HttpSession session,
                              ModelMap map, RedirectAttributes ra) {
        return "admin/seats";
    }

    @Override
    public String addSeats(Long screenId, HttpSession session,
                           ModelMap map, RedirectAttributes ra) {
        return "admin/add-seats";
    }

    @Override
    public String saveSeats(Long screenId, SeatLayoutForm form,
                            HttpSession session, RedirectAttributes ra) {
        return "redirect:/seats";
    }

    @Override
    public String manageMovies(HttpSession session, RedirectAttributes ra, ModelMap map) {
        return "admin/movies";
    }

    @Override
    public String loadAddMovie(MovieDto dto, RedirectAttributes ra, HttpSession session) {
        return "admin/add-movie";
    }

    @Override
    public String addMovie(MovieDto dto, BindingResult br,
                           RedirectAttributes ra, HttpSession session) {
        return "redirect:/movies";
    }

    @Override
    public String deleteMovie(Long id, HttpSession session, RedirectAttributes ra) {
        return "redirect:/movies";
    }

    @Override
    public String manageShows(Long movieId, ModelMap map,
                              RedirectAttributes ra, HttpSession session) {
        return "admin/shows";
    }

    @Override
    public String addShow(Long movieId, ModelMap map,
                          RedirectAttributes ra, HttpSession session) {
        return "admin/add-show";
    }

    @Override
    public String addShow(ShowDto dto, BindingResult br,
                          RedirectAttributes ra, HttpSession session, ModelMap map) {
        return "redirect:/shows";
    }

    @Override
    public String bookMovie(Long showId, HttpSession session,
                            RedirectAttributes ra, ModelMap map) {
        return "user/book";
    }

    @Override
    public String deleteShow(Long id, HttpSession session, RedirectAttributes ra) {
        return "redirect:/shows";
    }

    @Override
    public String displayShowsOnDate(LocalDate date, Long movieId,
                                     RedirectAttributes ra, ModelMap map) {
        return "shows";
    }

    @Override
    public String showSeats(Long showId, HttpSession session,
                            RedirectAttributes ra, ModelMap map) {
        return "seats";
    }

    @Override
    public String confirmBooking(Long showId, Long[] seatIds,
                                 HttpSession session, ModelMap map,
                                 RedirectAttributes ra) {
        return "redirect:/confirmation";
    }

    @Override
    public String confirmTicket(HttpSession session, ModelMap map,
                                RedirectAttributes ra, String email, String name) {
        return "ticket";
    }
}