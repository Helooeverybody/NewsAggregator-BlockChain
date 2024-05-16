package gui;

public class SearchFrame {
    private final HeaderPanel headerPanel;
    private final ContentPanel contentPanel;
    public SearchFrame (){
        this.headerPanel = new HeaderPanel();
        this.contentPanel = new ContentPanel();
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }
}
