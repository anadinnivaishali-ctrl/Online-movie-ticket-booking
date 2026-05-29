package com.jsp.book.service;

import com.jsp.book.dto.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.ModelMap;

import jakarta.servlet.http.HttpSession;

public interface UserService {

    String register(UserDto userDto, BindingResult result, RedirectAttributes attributes);

    String login(LoginDto dto, RedirectAttributes ra, HttpSession session);

    String logout(HttpSession session, RedirectAttributes ra);

    String loadMain(ModelMap map);

    String submitOtp(int id, String otp, RedirectAttributes ra);

    String resendOtp(String email, RedirectAttributes ra);

    String forgotPassword(String email, RedirectAttributes ra);

    String resetPassword(PasswordDto dto, BindingResult br,
                         RedirectAttributes ra, ModelMap map);

    String manageUsers(HttpSession session, RedirectAttributes ra, ModelMap map);

    String blockUser(Long id, HttpSession session, RedirectAttributes ra);

    String unBlockUser(Long id, HttpSession session, RedirectAttributes ra);

    String manageTheater(ModelMap map, RedirectAttributes ra, HttpSession session);

    String loadAddTheater(HttpSession session, RedirectAttributes ra, TheaterDto dto);

    String addTheater(HttpSession session, RedirectAttributes ra,
                      TheaterDto dto, BindingResult br);

    String deleteTheater(Long id, HttpSession session, RedirectAttributes ra);

    String editTheater(Long id, HttpSession session,
                       RedirectAttributes ra, ModelMap map);

    String updateTheater(HttpSession session, RedirectAttributes ra,
                         TheaterDto dto, BindingResult br, Long id);

    String manageScreens(Long theaterId, HttpSession session,
                         RedirectAttributes ra, ModelMap map);

    String addScreen(Long theaterId, HttpSession session,
                     RedirectAttributes ra, ModelMap map, ScreenDto dto);

    String addScreen(ScreenDto dto, BindingResult br,
                     HttpSession session, RedirectAttributes ra);

    String deleteScreen(Long id, HttpSession session, RedirectAttributes ra);

    String editScreen(Long id, HttpSession session,
                      RedirectAttributes ra, ModelMap map);

    String updateScreen(ScreenDto dto, Long id, BindingResult br,
                        HttpSession session, RedirectAttributes ra, ModelMap map);

    String manageSeats(Long screenId, HttpSession session,
                       ModelMap map, RedirectAttributes ra);

    String addSeats(Long screenId, HttpSession session,
                    ModelMap map, RedirectAttributes ra);

    String saveSeats(Long screenId, SeatLayoutForm form,
                     HttpSession session, RedirectAttributes ra);

    String manageMovies(HttpSession session, RedirectAttributes ra, ModelMap map);

    String loadAddMovie(MovieDto dto, RedirectAttributes ra, HttpSession session);

    String addMovie(MovieDto dto, BindingResult br,
                    RedirectAttributes ra, HttpSession session);

    String deleteMovie(Long id, HttpSession session, RedirectAttributes ra);

    String manageShows(Long movieId, ModelMap map,
                       RedirectAttributes ra, HttpSession session);

    String addShow(Long movieId, ModelMap map,
                   RedirectAttributes ra, HttpSession session);

    String addShow(ShowDto dto, BindingResult br,
                   RedirectAttributes ra, HttpSession session, ModelMap map);

    String bookMovie(Long showId, HttpSession session,
                     RedirectAttributes ra, ModelMap map);

    String deleteShow(Long id, HttpSession session, RedirectAttributes ra);

    String displayShowsOnDate(java.time.LocalDate date, Long movieId,
                              RedirectAttributes ra, ModelMap map);

    String showSeats(Long showId, HttpSession session,
                     RedirectAttributes ra, ModelMap map);

    String confirmBooking(Long showId, Long[] seatIds,
                          HttpSession session, ModelMap map,
                          RedirectAttributes ra);

    String confirmTicket(HttpSession session, ModelMap map,
                         RedirectAttributes ra, String email, String name);
}