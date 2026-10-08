import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

    // ==============================
    // COLORS
    // ==============================

    private final Color PRIMARY = new Color(37, 99, 235);
    private final Color PRIMARY_DARK = new Color(29, 78, 216);
    private final Color LIGHT_BLUE = new Color(239, 246, 255);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(31, 41, 55);
    private final Color SECONDARY_TEXT = new Color(107, 114, 128);
    private final Color SUCCESS = new Color(22, 163, 74);
    private final Color DANGER = new Color(220, 38, 38);
    private final Color BORDER = new Color(220, 225, 232);

    // ==============================
    // DATA
    // ==============================

    private ArrayList<Product> products;
    private Bill bill;

    // ==============================
    // GUI COMPONENTS
    // ==============================

    private JTextField customerField;
    private JComboBox<String> productComboBox;
    private JTextField quantityField;

    private JTable table;
    private DefaultTableModel tableModel;

    private JLabel subtotalLabel;
    private JLabel discountLabel;
    private JLabel gstLabel;
    private JLabel finalAmountLabel;

    // ==============================
    // CONSTRUCTOR
    // ==============================

    public Main() {

        createProducts();

        bill = new Bill();

        setTitle("Smart Bill Calculator");
        setSize(1100, 750);
        setMinimumSize(new Dimension(950, 650));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createGUI();
    }

    // ==============================
    // CREATE PRODUCTS
    // ==============================

    private void createProducts() {

        products = new ArrayList<>();

        products.add(new Product(1, "Pen", 10));
        products.add(new Product(2, "Notebook", 50));
        products.add(new Product(3, "Pencil", 5));
        products.add(new Product(4, "Eraser", 5));
        products.add(new Product(5, "School Bag", 800));
        products.add(new Product(6, "USB Cable", 120));
        products.add(new Product(7, "Mouse", 450));
        products.add(new Product(8, "Keyboard", 700));
        products.add(new Product(9, "Water Bottle", 150));
        products.add(new Product(10, "Calculator", 450));
    }

    // ==============================
    // CREATE GUI
    // ==============================

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(20, 25, 20, 25)
        );

        // ==========================
        // HEADER
        // ==========================

        JPanel header = createHeader();

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // ==========================
        // CENTER AREA
        // ==========================

        JPanel centerPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        centerPanel.setBackground(BACKGROUND);

        // Product input card
        JPanel inputCard = createInputCard();

        centerPanel.add(
                inputCard,
                BorderLayout.NORTH
        );

        // Table card
        JPanel tableCard = createTableCard();

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==========================
        // BOTTOM AREA
        // ==========================

        JPanel bottomPanel = new JPanel(
                new BorderLayout(15, 0)
        );

        bottomPanel.setBackground(BACKGROUND);

        JPanel summaryCard = createSummaryCard();

        JPanel buttonsCard = createButtonsCard();

        bottomPanel.add(
                summaryCard,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                buttonsCard,
                BorderLayout.EAST
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // ==============================
    // HEADER
    // ==============================

    private JPanel createHeader() {

        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setBackground(PRIMARY);

        header.setBorder(
                new EmptyBorder(18, 25, 18, 25)
        );

        JLabel title = new JLabel(
                "SMART BILL CALCULATOR"
        );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle = new JLabel(
                "Simple • Fast • Accurate Billing System"
        );

        subtitle.setForeground(
                new Color(219, 234, 254)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JPanel textPanel = new JPanel(
                new BorderLayout(0, 5)
        );

        textPanel.setOpaque(false);

        textPanel.add(
                title,
                BorderLayout.NORTH
        );

        textPanel.add(
                subtitle,
                BorderLayout.SOUTH
        );

        header.add(
                textPanel,
                BorderLayout.WEST
        );

        JLabel icon = new JLabel("₹");

        icon.setForeground(Color.WHITE);

        icon.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        45
                )
        );

        header.add(
                icon,
                BorderLayout.EAST
        );

        return header;
    }

    // ==============================
    // INPUT CARD
    // ==============================

    private JPanel createInputCard() {

        JPanel card = createCard();

        card.setLayout(
                new BorderLayout(15, 10)
        );

        JLabel heading = new JLabel(
                "Add Products"
        );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        heading.setForeground(TEXT);

        card.add(
                heading,
                BorderLayout.NORTH
        );

        JPanel fields = new JPanel(
                new GridBagLayout()
        );

        fields.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 8, 5, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Customer
        JLabel customerLabel =
                createLabel("Customer Name");

        customerField =
                new JTextField();

        styleTextField(customerField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        fields.add(
                customerLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        fields.add(
                customerField,
                gbc
        );

        // Product
        JLabel productLabel =
                createLabel("Product");

        productComboBox =
                new JComboBox<>();

        for (Product product : products) {

            productComboBox.addItem(
                    product.getId()
                            + " - "
                            + product.getName()
            );
        }

        styleComboBox(productComboBox);

        gbc.gridx = 2;
        gbc.weightx = 0;

        fields.add(
                productLabel,
                gbc
        );

        gbc.gridx = 3;
        gbc.weightx = 1;

        fields.add(
                productComboBox,
                gbc
        );

        // Quantity
        JLabel quantityLabel =
                createLabel("Quantity");

        quantityField =
                new JTextField();

        styleTextField(quantityField);

        gbc.gridx = 4;
        gbc.weightx = 0;

        fields.add(
                quantityLabel,
                gbc
        );

        gbc.gridx = 5;
        gbc.weightx = 0.5;

        fields.add(
                quantityField,
                gbc
        );

        // Add button
        JButton addButton =
                createButton(
                        "＋ Add Product",
                        PRIMARY
                );

        gbc.gridx = 6;
        gbc.weightx = 0;

        fields.add(
                addButton,
                gbc
        );

        // Remove button
        JButton removeButton =
                createButton(
                        "Remove",
                        DANGER
                );

        gbc.gridx = 7;

        fields.add(
                removeButton,
                gbc
        );

        card.add(
                fields,
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> addProduct()
        );

        removeButton.addActionListener(
                e -> removeProduct()
        );

        return card;
    }

    // ==============================
    // TABLE CARD
    // ==============================

    private JPanel createTableCard() {

        JPanel card = createCard();

        card.setLayout(
                new BorderLayout(0, 10)
        );

        JLabel heading = new JLabel(
                "Current Bill"
        );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        heading.setForeground(TEXT);

        card.add(
                heading,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Product",
                "Quantity",
                "Unit Price",
                "Total"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        table =
                new JTable(tableModel);

        table.setRowHeight(34);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        table.setForeground(TEXT);

        table.setBackground(Color.WHITE);

        table.setGridColor(BORDER);

        table.setSelectionBackground(
                LIGHT_BLUE
        );

        table.setSelectionForeground(
                TEXT
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        table.getTableHeader()
                .setBackground(
                        LIGHT_BLUE
                );

        table.getTableHeader()
                .setForeground(
                        PRIMARY_DARK
                );

        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                38
                        )
                );

        // Center table columns
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        table.getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );

        table.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        centerRenderer
                );

        table.getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return card;
    }

    // ==============================
    // SUMMARY CARD
    // ==============================

    private JPanel createSummaryCard() {

        JPanel card = createCard();

        card.setPreferredSize(
                new Dimension(
                        500,
                        150
                )
        );

        card.setLayout(
                new GridLayout(
                        4,
                        2,
                        15,
                        5
                )
        );

        JLabel subtotalText =
                createLabel("Subtotal");

        JLabel discountText =
                createLabel("Discount");

        JLabel gstText =
                createLabel("GST (18%)");

        JLabel finalText =
                createLabel("FINAL AMOUNT");

        subtotalLabel =
                createAmountLabel();

        discountLabel =
                createAmountLabel();

        gstLabel =
                createAmountLabel();

        finalAmountLabel =
                createAmountLabel();

        finalAmountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        finalAmountLabel.setForeground(
                SUCCESS
        );

        card.add(subtotalText);
        card.add(subtotalLabel);

        card.add(discountText);
        card.add(discountLabel);

        card.add(gstText);
        card.add(gstLabel);

        card.add(finalText);
        card.add(finalAmountLabel);

        return card;
    }

    // ==============================
    // BUTTON CARD
    // ==============================

    private JPanel createButtonsCard() {

        JPanel card = createCard();

        card.setPreferredSize(
                new Dimension(
                        270,
                        150
                )
        );

        card.setLayout(
                new GridLayout(
                        3,
                        1,
                        8,
                        8
                )
        );

        JButton generateButton =
                createButton(
                        "Generate Bill",
                        SUCCESS
                );

        JButton clearButton =
                createButton(
                        "Clear Bill",
                        PRIMARY
                );

        JButton exitButton =
                createButton(
                        "Exit",
                        DANGER
                );

        card.add(generateButton);
        card.add(clearButton);
        card.add(exitButton);

        generateButton.addActionListener(
                e -> generateBill()
        );

        clearButton.addActionListener(
                e -> clearBill()
        );

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        return card;
    }

    // ==============================
    // ADD PRODUCT
    // ==============================

    private void addProduct() {

        String quantityText =
                quantityField.getText().trim();

        if (quantityText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter quantity.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int quantity;

        try {

            quantity =
                    Integer.parseInt(
                            quantityText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (quantity <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than 0.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int index =
                productComboBox.getSelectedIndex();

        Product selectedProduct =
                products.get(index);

        bill.addItem(
                selectedProduct,
                quantity
        );

        double total =
                selectedProduct.getPrice()
                        * quantity;

        tableModel.addRow(
                new Object[]{
                        selectedProduct.getName(),
                        quantity,
                        String.format(
                                "\u20B9%.2f",
                                selectedProduct.getPrice()
                        ),
                        String.format(
                                "\u20B9%.2f",
                                total
                        )
                }
        );

        quantityField.setText("");

        updateSummary();
    }

    // ==============================
    // REMOVE PRODUCT
    // ==============================

    private void removeProduct() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a product from the table.",
                    "Remove Product",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Bill newBill = new Bill();

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            if (i == selectedRow) {
                continue;
            }

            String productName =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            int quantity =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(i, 1)
                                    .toString()
                    );

            for (Product product : products) {

                if (
                        product
                                .getName()
                                .equals(productName)
                ) {

                    newBill.addItem(
                            product,
                            quantity
                    );

                    break;
                }
            }
        }

        bill = newBill;

        tableModel.removeRow(
                selectedRow
        );

        updateSummary();
    }

    // ==============================
    // UPDATE SUMMARY
    // ==============================

    private void updateSummary() {

        double subtotal =
                bill.calculateSubtotal();

        double discount =
                bill.calculateDiscount();

        double gst =
                bill.calculateGST();

        double finalAmount =
                bill.calculateFinalAmount();

        subtotalLabel.setText(
                String.format(
                        "\u20B9%.2f",
                        subtotal
                )
        );

        discountLabel.setText(
                String.format(
                        "\u20B9%.2f",
                        discount
                )
        );

        gstLabel.setText(
                String.format(
                        "\u20B9%.2f",
                        gst
                )
        );

        finalAmountLabel.setText(
                String.format(
                        "\u20B9%.2f",
                        finalAmount
                )
        );
    }

    // ==============================
    // GENERATE BILL
    // ==============================

    private void generateBill() {

        String customerName =
                customerField.getText().trim();

        if (customerName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter customer name.",
                    "Customer Name",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one product.",
                    "Empty Bill",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        double subtotal =
                bill.calculateSubtotal();

        double discount =
                bill.calculateDiscount();

        double gst =
                bill.calculateGST();

        double finalAmount =
                bill.calculateFinalAmount();

        String message =
                "<html>"
                        + "<div style='width:350px;'>"
                        + "<h2>Bill Generated Successfully!</h2>"
                        + "<b>Customer:</b> "
                        + customerName
                        + "<br><br>"
                        + "<b>Subtotal:</b> "
                        + String.format(
                                "\u20B9%.2f",
                                subtotal
                        )
                        + "<br>"
                        + "<b>Discount:</b> "
                        + String.format(
                                "\u20B9%.2f",
                                discount
                        )
                        + "<br>"
                        + "<b>GST:</b> "
                        + String.format(
                                "\u20B9%.2f",
                                gst
                        )
                        + "<br><br>"
                        + "<h2>Final Amount: "
                        + String.format(
                                "\u20B9%.2f",
                                finalAmount
                        )
                        + "</h2>"
                        + "<br>"
                        + "Thank you for shopping!"
                        + "</div>"
                        + "</html>";

        JOptionPane.showMessageDialog(
                this,
                message,
                "SMART BILL CALCULATOR",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==============================
    // CLEAR BILL
    // ==============================

    private void clearBill() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to clear the current bill?",
                        "Clear Bill",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result
                        == JOptionPane.YES_OPTION
        ) {

            bill = new Bill();

            customerField.setText("");

            quantityField.setText("");

            productComboBox.setSelectedIndex(0);

            tableModel.setRowCount(0);

            updateSummary();
        }
    }

    // ==============================
    // CREATE CARD
    // ==============================

    private JPanel createCard() {

        JPanel panel = new JPanel();

        panel.setBackground(WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        return panel;
    }

    // ==============================
    // CREATE LABEL
    // ==============================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(TEXT);

        return label;
    }

    // ==============================
    // AMOUNT LABEL
    // ==============================

    private JLabel createAmountLabel() {

        JLabel label =
                new JLabel(
                        "\u20B90.00"
                );

        label.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(TEXT);

        return label;
    }

    // ==============================
    // TEXT FIELD STYLE
    // ==============================

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        150,
                        35
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );
    }

    // ==============================
    // COMBO BOX STYLE
    // ==============================

    private void styleComboBox(
            JComboBox<String> comboBox
    ) {

        comboBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        comboBox.setPreferredSize(
                new Dimension(
                        180,
                        35
                )
        );

        comboBox.setBackground(
                Color.WHITE
        );
    }

    // ==============================
    // BUTTON STYLE
    // ==============================

    private JButton createButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // ==============================
    // MAIN
    // ==============================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    Main window =
                            new Main();

                    window.setVisible(
                            true
                    );
                }
        );
    }
}