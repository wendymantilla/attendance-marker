package co.edu.poli.attendancemarker.controllers;

import co.edu.poli.attendancemarker.jpa.model.Record;
import co.edu.poli.attendancemarker.jpa.model.RecordType;
import co.edu.poli.attendancemarker.services.RecordDetailService;
import co.edu.poli.attendancemarker.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordDetailService recordDetailService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil tokenUtil;

    @GetMapping("/record")
    public ResponseEntity<List<Record>> getRecords(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userName = tokenUtil.getUsernameFromToken(token);
        UserDetails jwtUser = userDetailsService.loadUserByUsername(userName);
        return ResponseEntity.ok().body(recordDetailService.getRecords(jwtUser.getUsername()));
    }

    @PostMapping("/record")
    public ResponseEntity<String> saveRecord(@RequestParam RecordType recordType, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userName = tokenUtil.getUsernameFromToken(token);
        UserDetails jwtUser = userDetailsService.loadUserByUsername(userName);
        recordDetailService.saveRecord(jwtUser.getUsername(), recordType);
        return ResponseEntity.ok().body("Saved");
    }

}
