package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public DailyReport getLastReport() {
        List<DailyReport> allDailyReport = getAllDailyReport();
        return allDailyReport.get(allDailyReport.size() - 1);
    }

    public void addDailyReport (DailyReport dailyReport) {
        Transaction transaction = session.getTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public void delete () {
        session.beginTransaction();
        session.createQuery("DELETE from DailyReport").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
