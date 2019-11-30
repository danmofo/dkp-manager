package com.dmoffat.dkpmanager.model.pagination;

import java.util.List;

/**
 * Represents a page of results, used for lists of results which include pagination.
 *
 * @author Daniel Moffat
 */
public class Results<T> {

    /**
     * Total number of results found
     */
    private int numFound;

    /**
     * Total number of pages
     */
    private int numPages;

    /**
     * Items in this page of results, which items depends on the values of {@link Parameters}
     */
    private List<T> items;

    /**
     * Parameters used to fetch these results.
     */
    private Parameters parameters;

    private Results() {}

    public Results(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
        this.numPages = (int)Math.ceil((double)numFound / parameters.itemsPerPage);
    }

    public int prevPageNum() {
        if(parameters.page == 1) {
            return -1;
        }
        return parameters.page - 1;
    }

    public int nextPageNum() {
        if(parameters.page == numPages) {
            return -1;
        }
        return parameters.page + 1;
    }

    public boolean hasPrevPage() {
        return prevPageNum() != -1;
    }

    public boolean hasNextPage() {
        return nextPageNum() != -1;
    }

    public int getNumFound() { return numFound; }
    public int getNumPages() { return numPages; }
    public void setNumPages(int numPages) { this.numPages = numPages; }
    public List<T> getItems() { return items; }
    public void setItems(List<T> items) { this.items = items; }
    public Parameters getParameters() { return parameters; }
    public void setParameters(Parameters parameters) { this.parameters = parameters; }

    /**
     * Base set of parameters to fetch a page of results. Pretty much every search will contain these variables. To
     * add more parameters you can extend this class.
     */
    public static class Parameters {
        private int itemsPerPage = 10;
        private int page = 1;

        // Helper method to get the offset of this page in the data store (i.e. for page 2, start from the 10th result)
        public int getOffset() {
            return (page - 1) * itemsPerPage;
        }

        public int getItemsPerPage() { return itemsPerPage; }
        public void setItemsPerPage(int itemsPerPage) { this.itemsPerPage = itemsPerPage; }
        public int getPage() { return page; }
        public void setPage(int page) { this.page = page; }

        @Override
        public String toString() {
            return "Parameters{" +
                    "itemsPerPage=" + itemsPerPage +
                    ", page=" + page +
                    '}';
        }
    }
}
