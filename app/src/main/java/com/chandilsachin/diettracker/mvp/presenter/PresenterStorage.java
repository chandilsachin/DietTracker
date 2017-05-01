package com.chandilsachin.diettracker.mvp.presenter;

import java.util.HashMap;

/**
 * Created by BBI-M1025 on 15/06/16.
 */
public enum PresenterStorage
{
    INSTANCES;

    private HashMap<String, Presenter> idToPresenter = new HashMap<>();
    private HashMap<Presenter, String> presenterToId = new HashMap<>();

    public void add(final Presenter presenter)
    {
        final String id = presenter.getClass().getSimpleName() + "/" + System.nanoTime() + "/" + (int)(Math.random() * Integer.MAX_VALUE);
        idToPresenter.put(id,presenter);
        presenterToId.put(presenter, id);
        presenter.addOnDestroyListener(new Presenter.OnDestroyListener()
        {
            @Override
            public void onDestroy()
            {
                idToPresenter.remove(presenterToId.remove(presenter));
            }
        });

    }

    /**
     * Returns Presenter from given id, null if presenter does not exists.
     * @param id
     * @param <P>
     * @return
     */
    public <P> P getPresenter(String id)
    {
        return (P) idToPresenter.get(id);
    }

    /**
     * Returns id from given presenter returns null if presenter does not exists.
     * @param presenter
     * @return
     */
    public String getId(Presenter presenter)
    {
        return presenterToId.get(presenter);
    }

    public void clear()
    {
        idToPresenter.clear();
        presenterToId.clear();
    }


}
