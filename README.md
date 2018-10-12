# inputmaskvalidation
for the personal Use only:
                    if (etOutTime.length() == 5) {

                  SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                    Date date1 = null;
                    Date date2=null;
                    try {
                        date1 = format.parse(etInTime.getText().toString());
                         date2 = format.parse(etOutTime.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long mills = date1.getTime() - date2.getTime();
                    Log.v("Data1", ""+date1.getTime());
                    Log.v("Data1", ""+date2.getTime());
                    int hours = (int) (mills/(1000 * 60 * 60));
                    int mins = (int) (mills/(1000*60)) % 60;

                    String diff = hours + ":" + mins; // updated value every1 second
                    // Log.v("Data1", ""+diff);






                    }
