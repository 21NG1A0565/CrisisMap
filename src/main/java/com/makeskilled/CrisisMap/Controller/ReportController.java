package com.makeskilled.CrisisMap.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.makeskilled.CrisisMap.Entity.Report;
import com.makeskilled.CrisisMap.Repository.ReportRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/createReport")
    public String createReport() {
        return "createReport";
    }

    @GetMapping("/myreports")
    public String myReports(HttpSession session, Model model) {
        String loggedInUser = (String) session.getAttribute("username");
        List<Report> userReports = reportRepository.findBySubmittedBy(loggedInUser);
        model.addAttribute("reports", userReports);
        return "myreports";
    }

    @GetMapping("/ngoReports")
    public String myReports1(HttpSession session, Model model) {
        List<Report> userReports = reportRepository.findAll();
        model.addAttribute("reports", userReports);
        return "ngoReports";
    }



    @PostMapping("/submit-report")
    public String submitReport(@RequestParam String title,
                            @RequestParam String location,
                            @RequestParam String description,
                            @RequestParam String date,
                            HttpSession session) {
        Report report = new Report();
        report.setTitle(title);
        report.setLocation(location);
        report.setDescription(description);
        report.setDate(LocalDate.parse(date));

        String loggedInUser = (String) session.getAttribute("username");
        report.setSubmittedBy(loggedInUser);
        report.setStatus("pending");
        report.setRemarks("awaiting response");

        reportRepository.save(report);
        return "redirect:/myreports";
    }

    @PostMapping("/ngoReports/update")
    public String updateReportStatus(@RequestParam Long reportId, @RequestParam String status) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Report ID: " + reportId));

        report.setStatus(status); // Assuming status is a String. If it's an enum, use Status.valueOf(status)
        reportRepository.save(report);

        return "redirect:/ngoReports"; // Redirect back to the reports page
    }

    @PostMapping("/ngoReports/updateRemarks")
    public String updateReportRemarks(@RequestParam Long reportId, @RequestParam String remarks) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Report ID: " + reportId));

        report.setRemarks(remarks);
        reportRepository.save(report);

        return "redirect:/ngoReports"; // Redirect back to the reports page
    }
    
}
