package StreamSite.Services.Impl;

import StreamSite.DAO.AdminDAO;
import StreamSite.DAO.GameDAO;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.TweetInfo;
import StreamSite.Services.AdminService;
import StreamSite.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO= adminDAO;
    }


    @Override
    public void updatePageCount(int id) {
    adminDAO.updatePageCount(id);
    }
}
