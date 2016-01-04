import Schedule.*;
import Schedule.Action;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jakobs on 2015-11-23.
 */
public class UserGUI extends JFrame  {
    private JPanel rootPanel;
    private JList categoriesList;
    private JList actionsList;
    private JButton newCategoryButton;
    private JButton deleteCategoriesButton;
    private JButton updateActionButton;
    private JButton deleteActionButton;
    private JTextArea actionDetailTextArea;
    private JRadioButton completeRadioButton;
    private JRadioButton incompleteRadioButton;
    private JButton newActionButton;
    private JButton showChartButton;

    private ActionContainer actionContainer;

    public UserGUI(ActionContainer actionContainer) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setVisible(true);
        setSize(600, 500);

        this.actionContainer = actionContainer;

        refreshCategoriesList();
        refreshActionsList();

        newCategoryButton.addActionListener(a -> onNewCategoryButtonClick());
        deleteCategoriesButton.addActionListener(a -> onDeleteCategoriesButtonClick());
        newActionButton.addActionListener(a -> onNewActionButtonClick());
        updateActionButton.addActionListener(a -> onUpdateActionButtonClick());
        deleteActionButton.addActionListener(a -> onDeleteActionButtonClick());
        showChartButton.addActionListener(a -> showActionCompletionChart());

        completeRadioButton.addActionListener(a -> onCompleteActionsRadioButtonSwitch());
        incompleteRadioButton.addActionListener(a -> onIncompleteActionsRadioButtonSwitch());

        categoriesList.addListSelectionListener(e -> onCategoriesSelect(e));
        actionsList.addListSelectionListener(e -> onActionsSelect(e));
    }

    private void onCategoriesSelect(ListSelectionEvent e) {
        refreshActionsList();

        newActionButton.setEnabled(categoriesList.getSelectedValuesList().size() == 1);
    }

    private void onActionsSelect(ListSelectionEvent e) {
        if(actionsList.getSelectedValue() != null) {
            Action a = ((ActionIdentifier)actionsList.getSelectedValue()).action;

            updateActionButton.setEnabled(true);
            deleteActionButton.setEnabled(true);

            displayActionData(a);
        } else {

            updateActionButton.setEnabled(false);
            deleteActionButton.setEnabled(false);

            actionDetailTextArea.setText("No action selected...");

        }
    }

    private void showActionCompletionChart() {
        displayChart();
    }

    private void displayActionData(Action action) {
        actionDetailTextArea.setText("");

        actionDetailTextArea.append("Action\n");
        actionDetailTextArea.append("Name: " + action.name + "\n");
        actionDetailTextArea.append("Completion: " + action.completion *100 + "%\n");
        actionDetailTextArea.append("Category: " + action.category + "\n");
        actionDetailTextArea.append("Priority: " + action.priority + "\n");
    }

    private void onNewCategoryButtonClick() {

        String op = (String)JOptionPane.showInputDialog("Enter the name of your category: ");

        if(op != null) {
            actionContainer.addCategory(op);
            refreshCategoriesList();
        }
    }

    private void onDeleteCategoriesButtonClick() {
        categoriesList.getSelectedValuesList().forEach(c -> actionContainer.deleteCategory((String) c));
        refreshCategoriesList();
    }

    private void displayChart() {

        int totalActions = actionContainer.getAllActions().size();
        int completeActions = new ActionContainerReader(actionContainer).getFinishedActions().size();
        int incompleteActions = new ActionContainerReader(actionContainer).getUnfinishedActions().size();

        double completePercentage = (double) completeActions / (double) totalActions;
        double incompletePercentage = (double) incompleteActions / (double) totalActions;

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Complete", new Double(completePercentage));
        dataset.setValue("Incomplete", new Double(incompletePercentage));

        JFreeChart chart = ChartFactory.createPieChart(
                "Action completion chart",  // chart title
                dataset,             // data
                false,               // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);

        JOptionPane.showMessageDialog(null, new ChartPanel(chart), "", JOptionPane.OK_OPTION, null);
    }

    private void onNewActionButtonClick() {
        JPanel actionCreationPanel = new JPanel();
        Dimension dim = new Dimension(300, 150);
        actionCreationPanel.setSize(dim);
        actionCreationPanel.setPreferredSize(dim);

        actionCreationPanel.setLayout(new FlowLayout());

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 25));

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setPreferredSize(new Dimension(200, 75));

        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        JFormattedTextField priorityField = new JFormattedTextField(amountFormat);
        priorityField.setValue(new Double(1));
        priorityField.setPreferredSize(new Dimension(50, 25));

        actionCreationPanel.add(new JLabel("Name: "));
        actionCreationPanel.add(nameField);

        actionCreationPanel.add(new JLabel("Description: "));
        actionCreationPanel.add(descriptionArea);

        actionCreationPanel.add(new JLabel("Priority: "));
        actionCreationPanel.add(priorityField);

        Object res = JOptionPane.showConfirmDialog(null, actionCreationPanel, "New Action", JOptionPane.OK_CANCEL_OPTION);

        if(res != null) {
            String newName = nameField.getText();
            String desc = descriptionArea.getText();
            int p = Integer.parseInt(priorityField.getText());

            actionContainer.addAction(new Action(newName, (String)categoriesList.getSelectedValue(), desc, p ));

            refreshActionsList();
        }
    }

    private void onUpdateActionButtonClick() {
        String op = (String)JOptionPane.showInputDialog("Enter completion: ");

        if(op != null) {
            try {
                float completion = Float.parseFloat(op);

                Action a = ((ActionIdentifier)actionsList.getSelectedValue()).action;

                a.update(completion);

            } catch (NumberFormatException | InputMismatchException e) {
            }
        }
    }

    private void onDeleteActionButtonClick() {
        Action a = ((ActionIdentifier)actionsList.getSelectedValue()).action;

        actionContainer.deleteActionById(a.getId());

        refreshActionsList();
    }

    private void onCompleteActionsRadioButtonSwitch() {
        refreshActionsList();
    }

    private void onIncompleteActionsRadioButtonSwitch() {
        refreshActionsList();
    }

    private void refreshCategoriesList() {

        Object[] cat = actionContainer.getAllCategories().toArray();

        categoriesList.setListData(cat);

        categoriesList.revalidate();
    }

    private void refreshActionsList() {

        ActionContainerReader reader = new ActionContainerReader(actionContainer);

        List<String> categories = categoriesList.getSelectedValuesList();

        List<ActionIdentifier> actions = new ArrayList<>();
        categories.forEach(c -> actions.addAll(
                reader.getActionsByCategory(c).stream().filter(a -> {

                    boolean onlyComplete = completeRadioButton.isSelected();
                    boolean onlyIncomplete = incompleteRadioButton.isSelected();

                    if(onlyComplete && onlyIncomplete) return false;
                    if(onlyComplete) {
                        return a.completion == 1;
                    }
                    if(onlyIncomplete) {
                        return a.completion < 1;
                    }
                    return true;
                }).map(a -> new ActionIdentifier(a)).collect(Collectors.toList())
        ));

        actionsList.setListData(actions.toArray());

        actionsList.revalidate();

    }

    public class ActionIdentifier {
        public Action action;

        public ActionIdentifier(Action action) {
            this.action = action;
        }

        @Override
        public String toString() {
            return action.name;
        }
    }

}
