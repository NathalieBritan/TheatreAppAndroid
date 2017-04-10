package com.nathalieborodina.theatreapp.data.manager;

import com.nathalieborodina.theatreapp.data.model.DaoSession;
import com.nathalieborodina.theatreapp.data.model.PerfomanceEvent;
import com.nathalieborodina.theatreapp.data.model.PerfomanceEventDao;
import com.nathalieborodina.theatreapp.data.model.Ticket;
import com.nathalieborodina.theatreapp.data.model.TicketDao;

import java.util.Date;
import java.util.List;

/**
 * Created by nathalieborodina on 4/10/17.
 */

public class PerfomanceEventManager{

    private long mUserid;
    private  DataManager mDataManager;

    public PerfomanceEventManager(DataManager dataModel) {

            mDataManager = dataModel;

            if (mDataManager.getUserManager().getUserId() == null)
               mDataManager.getUserManager().checkUserLogged();

            mUserid = Long.parseLong(mDataManager.getUserManager().getUserId());
        }

        public List<PerfomanceEvent> getAllPerfomanceEvent() {
            mDataManager.getDaoSession().clear();
            PerfomanceEventDao tripDao = mDataManager.getDaoSession().getPerfomanceEventDao();
            return tripDao.queryBuilder().orderDesc(PerfomanceEventDao.Properties.Date_time).list();
        }

    public long countAllPerfomanceEvent() {
        mDataManager.getDaoSession().clear();
        return mDataManager.getDaoSession().getPerfomanceEventDao().
                queryBuilder().count();
    }


    public PerfomanceEvent addPerfomanceEvent(Long id, Date date, String title) {
        PerfomanceEvent event = new PerfomanceEvent(null, id, date, title);
        mDataManager.getDaoSession().getPerfomanceEventDao().insert(event);
        return event;
    }

    public PerfomanceEvent getPerfomanceEvent(long id) {
        DaoSession session = mDataManager.getDaoSession();
        session.clear();
        List<PerfomanceEvent> events = session.getPerfomanceEventDao().queryBuilder().where(PerfomanceEventDao.Properties.Id.eq(id)).list();
        if (events != null && events.size() > 0)
            return events.get(0);
        else
            return null;
    }

    public PerfomanceEvent getPerfomanceEventByEventId(long id) {
        DaoSession session = mDataManager.getDaoSession();
        session.clear();
        List<PerfomanceEvent> events = session.getPerfomanceEventDao().queryBuilder().where(PerfomanceEventDao.Properties.EventId.eq(id)).list();
        if (events != null && events.size() > 0)
            return events.get(0);
        else
            return null;
    }

    public void deleteTrip(PerfomanceEvent event) {
        mDataManager.getDaoSession().getPerfomanceEventDao().delete(event);
    }

    public List<Ticket> getAllTickets(long eventId) {
        return mDataManager.getDaoSession().getTicketDao().
                queryBuilder().
                where(TicketDao.Properties.EventId.eq(eventId)).
                orderDesc(TicketDao.Properties.Id).
                list();
    }

    public List<Ticket> getAllTicketsByCustomer(long customerId) {
        return mDataManager.getDaoSession().getTicketDao().
                queryBuilder().
                where(TicketDao.Properties.CustomerId.eq(customerId)).
                orderDesc(TicketDao.Properties.Id).
                list();
    }

    public Ticket getTicketById(long id) {
        DaoSession session = mDataManager.getDaoSession();
        session.clear();
        List<Ticket> tickets = session.getTicketDao().queryBuilder().where(TicketDao.Properties.TicketId.eq(id)).list();
        if (tickets != null && tickets.size() > 0)
            return tickets.get(0);
        else
            return null;
    }

    public Ticket addTicket(Long id, long ticketId, long eventId, int row, int seat, long customerId, String firstName, String lastname) {
        Ticket ticket= new Ticket(null, ticketId, eventId, row, seat, customerId, firstName, lastname);
        mDataManager.getDaoSession().getTicketDao().insert(ticket);
        return ticket;
    }

    public void deleteTicket(Ticket ticket) {
        mDataManager.getDaoSession().getTicketDao().delete(ticket);
    }

}
